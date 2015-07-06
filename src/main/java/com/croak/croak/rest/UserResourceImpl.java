package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.croak.croak.dao.UserDAO;
import com.croak.croak.dao.UserDAOImpl;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;

public class UserResourceImpl implements UserResource {

  private static UserDAO dao = new UserDAOImpl();

  public UserResourceImpl() {}

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
    // TODO: implement com.croak.croak.rest.UserResourceImpl.createUser
    return null;
  }

  @Override
  public Response updateUser(User user) {
    // TODO: implement com.croak.croak.rest.UserResourceImpl.updateUser
    return null;
  }
}
