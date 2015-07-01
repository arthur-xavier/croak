package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.croak.croak.dao.UserDAO;
import com.croak.croak.dao.UserDAOImpl;
import com.croak.croak.entities.User;

public class UserResourceImpl implements UserResource {

  private static final Logger logger = Logger.getLogger(UserResourceImpl.class);

  private static UserDAO dao = new UserDAOImpl();

  public UserResourceImpl() {}

  @Override
  public List<User> getUsers() {
    logger.info("* getUsers called");
    return dao.getUsers();
  }

  @Override
  public User getUser(@PathParam("id") Long id) {
    logger.info("* Received id in 'getCroak': "+id);
    return dao.getUser(id);
  }

  @Override
  public Response createUser(User user) {
    // TODO: implement this
    return null;
  }

  @Override
  public Response updateUser(User user) {
    // TODO: implement this
    return null;
  }
}
