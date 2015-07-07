package com.croak.croak.components;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.dao.CroakDAO;

public class UserProfile {

  @Inject
  private UserDAO userDao;
  @Inject
  private CroakDAO croakDao;

  @Parameter(required = true)
  @Property
  private User user;

  @Property
  private int followersNumber;
  @Property
  private int followingNumber;
  @Property
  private int croaksNumber;

  @BeginRender
  public void beginRender() throws UserNotFoundException {
    this.followingNumber = this.user.getSubscriptions().size();

    Set followers = new HashSet<User>();
    for(User u : userDao.getUsers()) {
      if(u.getSubscriptions().contains(this.user)) {
        followers.add(u);
      }
    }
    this.followersNumber = followers.size();

    this.croaksNumber = croakDao.getCroaksByUser(this.user.getUsername()).size();
  }
}
