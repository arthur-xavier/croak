package com.croak.croak.pages;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.croak.croak.entities.User;
import com.croak.croak.rest.UserResource;

public class Friends {

  @Inject
  private UserResource userResource;

  @Property
  private User user;

  @Property
  private Set<User> followers;

  @Property
  private Set<User> subscriptions;

  public void onActivate() {
    this.user = userResource.getUser("mustermann");
    this.subscriptions = this.user.getSubscriptions();
    this.followers = new HashSet<User>();

    for(User u : userResource.getUsers()) {
      if(u.getSubscriptions().contains(this.user)) {
        this.followers.add(u);
      }
    }
  }
}
