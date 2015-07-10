package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.TextArea;

import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.exceptions.UserNotFoundException;

@RequiresAuthentication
public class Profile {

  @Inject
  private UserDAO userDao;

  @Property
  private User user;

  @Component
  private Form form;

  @Property
  @Component(id = "username", parameters={"value=user.username"})
  private TextField usernameField;
  @Property
  @Component(id = "password", parameters={"value=user.password"})
  private TextField passwordField;
  @Property
  @Component(id = "confirmPassword", parameters={"value=user.password"})
  private TextField confirmPassword;
  @Property
  @Component(id = "firstName", parameters={"value=user.firstName"})
  private TextField firstNameField;
  @Property
  @Component(id = "lastName", parameters={"value=user.lastName"})
  private TextField lastNameField;
  @Property
  @Component(id = "email", parameters={"value=user.email"})
  private TextField emailField;
  @Property
  @Component(id = "avatar", parameters={"value=user.avatar"})
  private TextField avatarField;
  @Property
  @Component(id = "quote", parameters={"value=user.quote"})
  private TextArea quoteField;

  public void onActivate() throws UserNotFoundException {
    this.user = userDao.getUser((String)SecurityUtils.getSubject().getPrincipal());
  }

  public Object onSuccess() {
    // TODO: add validations to com.croak.croak.pages.Profile.onSuccess
    userDao.saveUser(user);
    return this;
  }
}
