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
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

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
   * Return the list of croaks containing the specified String
   * @param query the String to search for
   * @return list of croaks containing the query String
   */
  @GET
  @Path("find/{query}")
  @Produces("application/json")
  List<Croak> findCroaks(@PathParam("query") String query) throws CroakNotFoundException;

  /**
   * Return the list of croaks created by @username
   * @return list of croaks created by @username
   */
  @GET
  @Path("@{username}")
  @Produces(MediaType.APPLICATION_JSON)
  List<Croak> getCroaksByUser(@PathParam("username") String username) throws UserNotFoundException;

  /**
   * Return the list of croaks created by the users who @username follows
   * @return list of croaks @username can see
   */
  @GET
  @Path("for/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  List<Croak> getCroaksForUser(@PathParam("username") String username) throws UserNotFoundException;

  /**
   * Find a croak with a specific id number
   * @param id croak's id to search for
   * @return croak found whose id = id
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Croak getCroak(@PathParam("id") Long id) throws CroakNotFoundException;

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
