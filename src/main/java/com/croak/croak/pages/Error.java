package com.croak.croak.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.services.ExceptionReporter;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

/**
 * Customized errror handling page
 */
@RequiresAuthentication
public class Error implements ExceptionReporter {

  @Property
  @Persist(PersistenceConstants.FLASH)
  private String error;

  public void reportException(Throwable exception) {
    error = exception.getLocalizedMessage();
  }

  public Object setupRender() {
    if(error.equals("The current Subject is not authenticated. Access denied.")) {
      return Login.class;
    }
    return true;
  }
}
