package com.croak.croak.services;

import java.io.IOException;

import org.apache.tapestry5.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Autobuild;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.ExceptionReporter;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.RequestExceptionHandler;
import org.apache.tapestry5.services.ResponseRenderer;
import org.apache.tapestry5.services.URLEncoder;
import org.slf4j.Logger;

import org.tynamo.routing.Route;
import org.tynamo.routing.services.RouteFactory;
import org.tynamo.routing.services.RouteProvider;
import org.tynamo.security.SecuritySymbols;
import org.tynamo.security.services.SecurityFilterChainFactory;
import org.tynamo.security.services.impl.SecurityFilterChain;
import org.tynamo.security.shiro.authc.FormAuthenticationFilter;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.authz.UnauthenticatedException;
import com.croak.croak.security.UserRealm;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{

    @Contribute(javax.ws.rs.core.Application.class)
    public static void configureRestResources(Configuration<Object> singletons,
      com.croak.croak.rest.CroakResource croakResource,
      com.croak.croak.rest.UserResource userResource)
    {
        singletons.add(croakResource);
        singletons.add(userResource);
    }

    @Contribute(org.apache.shiro.web.mgt.WebSecurityManager.class)
    public static void addRealms(Configuration<Realm> configuration, @Autobuild UserRealm userRealm) {
      configuration.add(userRealm);
    }
    public static void contributeSecurityConfiguration(Configuration<SecurityFilterChain> configuration,
      SecurityFilterChainFactory factory)
    {
      configuration.add(factory.createChain("/login").add(factory.anon()).build());
      configuration.add(factory.createChain("/assets/**").add(factory.anon()).build());
      configuration.add(factory.createChain("/css/**").add(factory.anon()).build());
      configuration.add(factory.createChain("/font/**").add(factory.anon()).build());
      configuration.add(factory.createChain("/img/**").add(factory.anon()).build());
      configuration.add(factory.createChain("/js/**").add(factory.anon()).build());
      configuration.add(factory.createChain("/rest/**").add(factory.anon()).build());
      //configuration.add(factory.createChain("/**").add(factory.user()).build());
    }

    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        binder.bind(com.croak.croak.rest.CroakResource.class, com.croak.croak.rest.CroakResourceImpl.class);
        binder.bind(com.croak.croak.rest.UserResource.class, com.croak.croak.rest.UserResourceImpl.class);

        // use Map
        //binder.bind(com.croak.croak.dao.CroakDAO.class, com.croak.croak.dao.CroakDAOImpl.class);
        //binder.bind(com.croak.croak.dao.UserDAO.class, com.croak.croak.dao.UserDAOImpl.class);

        // use Hibernate
        binder.bind(com.croak.croak.dao.CroakDAO.class, com.croak.croak.dao.hibernate.CroakDAOHibernateImpl.class);
        binder.bind(com.croak.croak.dao.UserDAO.class, com.croak.croak.dao.hibernate.UserDAOHibernateImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    // http://tapestry.apache.org/hibernate-user-guide.html
    @Match("*DAO")
    public static void adviseTransactions(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver)
    {
        advisor.addTransactionCommitAdvice(receiver);
    }

    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions. This overrides Tapesty's default
        // (a random hexadecimal number), but may be further overriden by DevelopmentModule or
        // QaModule.
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
        // Tynamo's tapestry-security module configuration
        configuration.add(SecuritySymbols.LOGIN_URL, "/login");
        configuration.add(SecuritySymbols.UNAUTHORIZED_URL, "/login");
        configuration.add(SecuritySymbols.SUCCESS_URL, "/index");
    }


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * <p/>
     * <p/>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     * <p/>
     * <p/>
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
                                         @Local
                                         RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }

    /**
    * Decorate Error page
    * @param logger
    * @param renderer
    * @param componentSource
    * @param productionMode
    * @param service
    * @return
    */
    public RequestExceptionHandler decorateRequestExceptionHandler(
              final Logger logger,
              final ResponseRenderer renderer,
              final ComponentSource componentSource,
              @Symbol(SymbolConstants.PRODUCTION_MODE)
              boolean productionMode,
              Object service)
    {
        //if(!productionMode) { return null; }
        return new RequestExceptionHandler() {
            public void handleRequestException(Throwable exception)
            throws IOException {
                logger.error("Unexpected runtime exception: " + exception.getMessage(), exception);
                if(exception.getClass() == UnauthenticatedException.class) {
                  renderer.renderPageMarkupResponse("Login");
                } else {
                  ((ExceptionReporter)componentSource.getPage("Error")).reportException(exception);
                  renderer.renderPageMarkupResponse("Error");
                }
            }
        };
    }

    public static void contributeServiceOverride(MappedConfiguration<Class, Object> configuration) {
        configuration.add(URLEncoder.class, new CroakURLEncoderImpl());
    }

    @Primary @Contribute(RouteProvider.class)
    public static void addRoutes(OrderedConfiguration<Route> configuration, RouteFactory routeFactory) {
        configuration.add("home", routeFactory.create("/", "Home"));
        configuration.add("viewuser", routeFactory.create("/@{0}", "ViewUser"));
        configuration.add("viewcroak", routeFactory.create("/croak/{0}", "ViewCroak"));
    }
}
