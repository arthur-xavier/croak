package com.croak.croak.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.routing.annotations.At;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

@RequiresAuthentication
public class Search {

  @Property
  @ActivationRequestParameter(value = "q")
  private String query;

  @Property
  private String userQuery = null;

  @Property
  private String croakQuery = null;

  public void onActivate() throws Exception {
    if(this.query.isEmpty()) throw new Exception("Empty search.");
    if(this.query.charAt(0) == '@')
      this.userQuery = query.substring(1).toLowerCase();
    else
      this.croakQuery = query.toLowerCase();
  }
}
