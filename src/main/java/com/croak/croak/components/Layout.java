package com.croak.croak.components;

import java.util.List;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.corelib.components.Form;

import org.apache.shiro.SecurityUtils;

import com.croak.croak.dao.CroakDAO;
import com.croak.croak.dao.InvitationDAO;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.entities.Croak;
import com.croak.croak.entities.Invitation;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.pages.Home;

public class Layout {

  @Inject
  private CroakDAO croakDao;
  @Inject
  private UserDAO userDao;
  @Inject
  private InvitationDAO invitationDAO;

  /**
   * The page title, for the <title> element and the <h1> element.
   */
  @Property
  @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
  private String title;

  @Property
  private String pageName;

  @Inject
  private ComponentResources resources;

  @Component
  private Form croakForm;
  @Property
  private String text;
  @Property
  private String color;

  @Property
  private Invitation invitation;

  public List<Invitation> getInvitations() throws UserNotFoundException {
    return invitationDAO.getInvitationsForUser((String)SecurityUtils.getSubject().getPrincipal());
  }

  public Object onSuccessFromCroakForm() throws UserNotFoundException {
    User author = userDao.getUser((String)SecurityUtils.getSubject().getPrincipal());
    Croak croak = new Croak(text, color, author);
    croakDao.saveCroak(croak);
    return Home.class;
  }

  public String getClassForPageName() {
    return resources.getPageName().equalsIgnoreCase(pageName)
        ? "active"
        : null;
  }

  public String[] getPageNames() {
    return new String[]{"Home", "Friends", "Profile", "Logout"};
  }
}
