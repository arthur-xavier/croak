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

import com.croak.croak.dao.CroakDAO;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class CroakResourceImpl implements CroakResource {

  @Inject
  private CroakDAO dao;
  @Inject
  private UserDAO userDao;

  @Override
  public List<Croak> getCroaks() {
    return dao.getCroaks();
  }

  @Override
  public List<Croak> findCroaks(@PathParam("query") String query) throws CroakNotFoundException {
    return dao.findCroaks(query);
  }

  @Override
  public List<Croak> getCroaksByUser(String username) throws UserNotFoundException {
    return dao.getCroaksByUser(username);
  }

  @Override
  public List<Croak> getCroaksForUser(String username) throws UserNotFoundException {
    return dao.getCroaksForUser(username);
  }

  @Override
  public Croak getCroak(@PathParam("id") Long id) throws CroakNotFoundException {
    return dao.getCroak(id);
  }

  protected void authorize(HttpHeaders httpHeaders) throws UserNotFoundException, UnauthorizedException {
    String authString = httpHeaders.getRequestHeader("Authorization").get(0);
    String credentials = new String(Base64.decodeBase64(authString));
    String username = credentials.substring(0, credentials.indexOf(":"));
    String password = credentials.substring(credentials.indexOf(":") + 1);

    User user = userDao.getUser(username);
    if(!user.getPassword().equals(password)) {
      throw new UnauthorizedException("Unauthorized access.");
    }
  }

  @Override
  public Response createCroak(@Context HttpHeaders httpHeaders, Croak croak) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response updateCroak(@Context HttpHeaders httpHeaders, Croak croak) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response deleteCroak(@Context HttpHeaders httpHeaders, @PathParam("id") Long id) {
    try {
      this.authorize(httpHeaders);
    } catch(Exception e) {
      return Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).entity("Unauthorized access").build();
    }

    try {
      dao.removeCroak(id);
    } catch(CroakNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    return Response.ok(id).build();
  }
}
