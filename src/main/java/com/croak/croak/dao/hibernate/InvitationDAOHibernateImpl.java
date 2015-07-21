package com.croak.croak.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.croak.croak.entities.Invitation;
import com.croak.croak.entities.User;
import com.croak.croak.dao.InvitationDAO;
import com.croak.croak.exceptions.InvitationNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class InvitationDAOHibernateImpl implements InvitationDAO {

  @Inject
  private Session session;

  @Override
  public List<Invitation> getInvitations() {
    return session.createCriteria(Invitation.class).list();
  }

  @Override
  public List<Invitation> getInvitationsBySender(String username) throws UserNotFoundException {
    return session.createCriteria(Invitation.class)
                  .createAlias("sender", "s")
                  .add(Restrictions.eq("s.username", username))
                  .list();
  }

  @Override
  public List<Invitation> getInvitationsForUser(String username) throws UserNotFoundException {
    return session.createCriteria(Invitation.class)
                  .createAlias("user", "u")
                  .add(Restrictions.eq("u.username", username))
                  .list();
  }

  @Override
  public Invitation saveInvitation(Invitation invitation) {
    session.persist(invitation);
    return invitation;
  }

  @Override
  public void removeInvitation(Long id) throws InvitationNotFoundException {
    session.delete(
      session.createCriteria(Invitation.class)
             .add(Restrictions.eq("id", id))
             .uniqueResult());
  }
}
