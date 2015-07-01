package com.croak.croak.pages;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.croak.croak.entities.User;
import com.croak.croak.rest.UserResource;
import com.croak.croak.rest.CroakResource;

public class Home {

  @Inject
  private UserResource userResource;
  @Inject
  private CroakResource croakResource;

  @Property
  private User user;

  @Property
  private int followersNumber;
  @Property
  private int followingNumber;
  @Property
  private int croaksNumber;

  public void onActivate() {
    this.user = userResource.getUser(0L);

    this.followingNumber = this.user.getSubscriptions().size();

    Set followers = new HashSet<User>();
    for(User u : userResource.getUsers()) {
      if(u.getSubscriptions().contains(this.user)) {
        followers.add(u);
      }
    }
    this.followersNumber = followers.size();

    this.croaksNumber = croakResource.getCroaksByUser(this.user.getUsername()).size();
  }
}
