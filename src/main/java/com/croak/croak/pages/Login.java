package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.beaneditor.Validate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;

import org.apache.log4j.Logger;

import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAO;

public class Login {

  private static Logger log = Logger.getLogger(Login.class);

  @Inject
  private UserDAO userDao;

  @Property
  @Validate("required, minlength=3, maxlength=50")
  private String usernameLogin;
  @Property
  @Validate("required, minlength=3")
  private String passwordLogin;

  @Property
  @Validate("required, minlength=3, maxlength=50")
  private String username;
  @Property
  @Validate("required, minlength=3")
  private String password;
  @Property
  @Validate("required, minlength=3")
  private String confirmPassword;
  @Property
  @Validate("required, minlength=3, maxlength=50")
  private String firstName;
  @Property
  @Validate("required, minlength=3, maxlength=50")
  private String lastName;
  @Property
  @Validate("required, email")
  private String email;

  @Component
  private Form loginForm;

  @Component
  private Form signupForm;

  public Object onSuccessFromLoginForm() {
    UsernamePasswordToken token = new UsernamePasswordToken(usernameLogin, passwordLogin, true);
    log.info("Got " + usernameLogin + ":" + passwordLogin);
    try {
      SecurityUtils.getSubject().login(token);
    } catch (AuthenticationException e) {
      loginForm.recordError("Invalid username or password.");
      return this;
    }
    return Home.class;
  }

  public Object onSuccessFromSignupForm() {
    if(!password.equals(confirmPassword)) {
      signupForm.recordError("Password and password confirmation must be equal.");
      return this;
    }

    User user = new User(username, firstName, lastName);
    user.setPassword(password);
    user.setEmail(email);

    try {
      userDao.saveUser(user);
    } catch(Exception e) {
      signupForm.recordError("Username or email already taken.");
      return this;
    }

    try {
      UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), true);
      SecurityUtils.getSubject().login(token);
    } catch(AuthenticationException e) {
      signupForm.recordError("Invalid username or password.");
      return this;
    }

    return Home.class;
  }
}
