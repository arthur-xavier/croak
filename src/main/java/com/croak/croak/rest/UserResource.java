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

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;

@Path("/user")
public interface UserResource {
  @GET
  @Produces("application/json")
  List<User> getUsers();

  @GET
  @Path("find/{username}")
  @Produces("application/json")
  List<User> findUsers(@PathParam("username") String username) throws UserNotFoundException;

  @GET
  @Path("{username}")
  @Produces("application/json")
  User getUser(@PathParam("username") String username) throws UserNotFoundException;

  @POST
  @Consumes("application/json")
  Response createUser(User user);

  @PUT
  @Consumes("application/json")
  Response updateUser(User user);
}
