package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.croak.croak.dao.CroakDAO;
import com.croak.croak.dao.CroakDAOImpl;
import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.CroakNotFoundException;

public class CroakResourceImpl implements CroakResource {

  private static final Logger logger = Logger.getLogger(CroakResourceImpl.class);

  private static CroakDAO dao = new CroakDAOImpl();

  private static List<Croak> croaks = new ArrayList<Croak>();

  public CroakResourceImpl() {}

  @Override
  public List<Croak> getCroaks() {
    logger.info("* getCroaks called");
    return dao.getCroaks();
  }

  @Override
  public List<Croak> getCroaksByUser(String username) {
    logger.info("* getCroaksByUsers called");
    return dao.getCroaksByUser(username);
  }

  @Override
  public Croak getCroak(@PathParam("id") Long id) {
    logger.info("* Received id in getCroak: "+id);
    return dao.getCroak(id);
  }

  @Override
  public Response createCroak(Croak croak) {
    logger.info("* createCroak called");
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response updateCroak(Croak croak) {
    logger.info("* updateCroak called");
    return Response.ok(dao.saveCroak(croak), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response deleteCroak(@PathParam("id") Long id) {
    logger.info("* deleteCroak called");
    try {
      dao.removeCroak(id);
    } catch(CroakNotFoundException e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
    return Response.ok(id).build();
  }
}
