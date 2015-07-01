package com.croak.croak.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
  @Produces(MediaType.APPLICATION_JSON)
  List<Croak> getCroaks();

  /**
   * Return the list of croaks created by @username
   * @return list of croaks created by @username
   */
  @GET
  @Path("@{username}")
  @Produces(MediaType.APPLICATION_JSON)
  List<Croak> getCroaksByUser(@PathParam("username") String username);

  /**
   * Find a croak with a specific id number
   * @param id croak's id to search for
   * @return croak found whose id = id
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Croak getCroak(@PathParam("id") Long id);

  /**
   *
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response createCroak(Croak croak);

  /**
   *
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  Response updateCroak(Croak croak);

  /**
   *
   */
  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteCroak(@PathParam("id") Long id);
}
