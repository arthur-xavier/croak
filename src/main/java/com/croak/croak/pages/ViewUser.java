package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.routing.annotations.At;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.rest.UserResource;

public class ViewUser {

  @Inject
  private UserResource userResource;

  @Property
  private User user;

  public void onActivate(String username) throws UserNotFoundException {
    this.user = userResource.getUser(username);
  }
}
