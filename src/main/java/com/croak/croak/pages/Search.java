package com.croak.croak.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.routing.annotations.At;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.rest.UserResource;

public class Search {

  @Property
  @ActivationRequestParameter(value = "q")
  private String query;

  @Property
  private String userQuery = null;

  @Property
  private String croakQuery = null;

  public void onActivate() throws Exception{
    if(query.isEmpty()) throw new Exception("Empty search.");
    if(query.charAt(0) == '@')
      this.userQuery = query.substring(1).toLowerCase();
    else
      this.croakQuery = query.toLowerCase();
  }

  /*@Inject
  private UserResource userResource;
  @Inject
  private CroakResource croakResource;

  @Property
  private List<User> users;
  @Property
  private List<Croak> croaks;

  public void onActivate() throws CroakNotFoundException, UserNotFoundException {
    if(query.charAt(0) == '@') {
      this.users = userResource.findUsers(query.substring(1));
      if(this.users == null) {
        throw new UserNotFoundException("User " + query + " not found.");
      }
    } else {
      this.croaks = croakResource.findCroaks(query);
      if(this.croaks == null) {
        throw new CroakNotFoundException("No croak found containing " + query);
      }
    }
  }*/
}
