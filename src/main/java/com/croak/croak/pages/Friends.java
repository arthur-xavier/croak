package com.croak.croak.pages;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.dao.UserDAO;

@RequiresAuthentication
public class Friends {

  @Inject
  private UserDAO userDao;

  @Property
  private User user;

  @Property
  private Set<User> followers;

  @Property
  private Set<User> subscriptions;

  public Object onActionFromFollow(String username) throws UserNotFoundException {
    userDao.followUser(username);
    return this;
  }

  public Object onActionFromUnfollow(String username) throws UserNotFoundException {
    userDao.unfollowUser(username);
    return this;
  }

  public void onActivate() throws UserNotFoundException {
    this.user = userDao.getUser((String)SecurityUtils.getSubject().getPrincipal());
    this.subscriptions = this.user.getSubscriptions();
    this.followers = this.user.getFollowers();
  }
}
