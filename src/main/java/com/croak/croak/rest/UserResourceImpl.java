package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.authz.UnauthorizedException;

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

  protected void authorize(HttpHeaders httpHeaders) throws UserNotFoundException, UnauthorizedException {
    String authString = httpHeaders.getRequestHeader("Authorization").get(0);
    String credentials = new String(Base64.decodeBase64(authString));
    String username = credentials.substring(0, credentials.indexOf(":"));
    String password = credentials.substring(credentials.indexOf(":") + 1);

    User user = dao.getUser(username);
    if(!user.getPassword().equals(password)) {
      throw new UnauthorizedException("Unauthorized access.");
    }
  }

  @Override
  public Response createUser(@Context HttpHeaders httpHeaders, User user) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    return Response.ok(dao.saveUser(user), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response updateUser(@Context HttpHeaders httpHeaders, User user) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    return Response.ok(dao.saveUser(user), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response followUser(@Context HttpHeaders httpHeaders, User user) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    try {
      dao.followUser(user.getUsername());
    } catch(UserNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity("User not found: " + user.getUsername()).build();
    } catch(Exception e) {
      return Response.serverError().build();
    }
    return Response.ok(user, MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response unfollowUser(@Context HttpHeaders httpHeaders, User user) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    try {
      dao.unfollowUser(user.getUsername());
    } catch(UserNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND).entity("User not found: " + user.getUsername()).build();
    } catch(Exception e) {
      return Response.serverError().build();
    }
    return Response.ok(user, MediaType.APPLICATION_JSON).build();
  }
}
