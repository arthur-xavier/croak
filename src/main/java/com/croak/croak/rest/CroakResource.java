package com.croak.croak.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.croak.croak.entities.Croak;

@Path("/croak")
public interface CroakResource {
  @GET
  @Produces("application/json")
  List<Croak> getCroaks();

  @GET
  @Path("{id}")
  @Produces("application/json")
  Croak getCroak(@PathParam("id") Long id);
}
