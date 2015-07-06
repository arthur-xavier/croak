package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.croak.croak.dao.CroakDAO;
import com.croak.croak.dao.CroakDAOImpl;
import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class CroakResourceImpl implements CroakResource {

  private static CroakDAO dao = new CroakDAOImpl();

  private static List<Croak> croaks = new ArrayList<Croak>();

  public CroakResourceImpl() {}

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

  @Override
  public Response createCroak(Croak croak) {
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response updateCroak(Croak croak) {
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response deleteCroak(@PathParam("id") Long id) {
    try {
      dao.removeCroak(id);
    } catch(CroakNotFoundException e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(id).build();
  }
}
