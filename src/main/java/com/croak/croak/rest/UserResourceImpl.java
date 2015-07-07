package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.tapestry5.ioc.annotations.Inject;

import com.croak.croak.dao.UserDAO;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;

public class UserResourceImpl implements UserResource {

  @Inject
  private UserDAO dao;

  @Override
  public List<User> getUsers() {
    return dao.getUsers();
  }

  @Override
  public List<User> findUsers(@PathParam("username") String username) throws UserNotFoundException {
    return dao.findUsers(username);
  }

  @Override
  public User getUser(@PathParam("username") String username) throws UserNotFoundException {
    return dao.getUser(username);
  }

  @Override
  public Response createUser(User user) {
    return Response.ok(dao.saveUser(user), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response updateUser(User user) {
    return Response.ok(dao.saveUser(user), MediaType.APPLICATION_JSON).build();
  }
}
