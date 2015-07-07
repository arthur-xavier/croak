package com.croak.croak.pages;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.dao.UserDAO;

public class Friends {

  @Inject
  private UserDAO userDao;

  @Property
  private User user;

  @Property
  private Set<User> followers;

  @Property
  private Set<User> subscriptions;

  public void onActivate() throws UserNotFoundException {
    this.user = userDao.getUser("mustermann");
    this.subscriptions = this.user.getSubscriptions();
    this.followers = this.user.getFollowers();
  }
}
