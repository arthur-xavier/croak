package com.croak.croak.rest;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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

import com.croak.croak.entities.Invitation;
import com.croak.croak.exceptions.InvitationNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

@Path("/invitation")
public interface InvitationResource {

  /**
   * Return the list of all croaks
   * @return list of all saved croaks
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Invitation> getInvitations();

  /**
   * Return the list of croaks created by @username
   * @return list of croaks created by @username
   */
  @GET
  @Path("@{username}")
  @Produces(MediaType.APPLICATION_JSON)
  List<Invitation> getInvitationsBySender(@PathParam("username") String username) throws UserNotFoundException;

  /**
   * Return the list of croaks created by the users who @username follows
   * @return list of croaks @username can see
   */
  @GET
  @Path("for/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  List<Invitation> getInvitationsForUser(@PathParam("username") String username) throws UserNotFoundException;

  /**
   *
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response createInvitation(@Context HttpHeaders httpHeaders, Invitation croak);

  /**
   *
   */
  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Response deleteInvitation(@Context HttpHeaders httpHeaders, @PathParam("id") Long id);
}
