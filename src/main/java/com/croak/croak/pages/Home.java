package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.croak.croak.entities.User;
import com.croak.croak.rest.UserResource;

public class Home {

  @Inject
  private UserResource userResource;

  @Property
  private User user;

  public void onActivate() {
    this.user = userResource.getUser(0L);
  }
}
