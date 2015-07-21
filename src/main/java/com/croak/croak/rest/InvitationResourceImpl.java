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

import com.croak.croak.dao.InvitationDAO;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.entities.Invitation;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.InvitationNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class InvitationResourceImpl implements InvitationResource {

  @Inject
  private InvitationDAO dao;
  @Inject
  private UserDAO userDao;

  @Override
  public List<Invitation> getInvitations() {
    return dao.getInvitations();
  }

  @Override
  public List<Invitation> getInvitationsBySender(String username) throws UserNotFoundException {
    return dao.getInvitationsBySender(username);
  }

  @Override
  public List<Invitation> getInvitationsForUser(String username) throws UserNotFoundException {
    return dao.getInvitationsForUser(username);
  }

  @Override
  public Response createInvitation(@Context HttpHeaders httpHeaders, Invitation invitation) {
    return Response.ok(dao.saveInvitation(invitation), MediaType.APPLICATION_JSON).build();
  }

  @Override
  public Response deleteInvitation(@Context HttpHeaders httpHeaders, @PathParam("id") Long id) {
    try {
      dao.removeInvitation(id);
    } catch(InvitationNotFoundException e) {
      return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }
    return Response.ok("{\"id\": \"" + id + "\"}", MediaType.APPLICATION_JSON).build();
  }
}
