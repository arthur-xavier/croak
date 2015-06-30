package com.croak.croak.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.croak.croak.entities.Croak;

@Path("/croak")
public interface CroakResource {

  /**
   * Return the list of all croaks
   * @return list of all saved croaks
   */
  @GET
  @Produces("application/json")
  List<Croak> getCroaks();

  /**
   * Find a croak with a specific id number
   * @param id croak's id to search for
   * @return croak found whose id = id
   */
  @GET
  @Path("{id}")
  @Produces("application/json")
  Croak getCroak(@PathParam("id") Long id);

  /**
   *
   */
  @POST
  @Consumes("application/json")
  Response createCroak(Croak croak);

  /**
   *
   */
  @PUT
  @Consumes("application/json")
  Response updateCroak(Croak croak);
}
