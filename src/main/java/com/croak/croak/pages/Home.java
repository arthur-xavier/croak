package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.exceptions.UserNotFoundException;

@RequiresAuthentication
public class Home {

  @Inject
  private UserDAO userDao;

  @Property
  private User user;

  public void onActivate() throws UserNotFoundException {
    this.user = userDao.getUser((String)SecurityUtils.getSubject().getPrincipal());
  }
}
