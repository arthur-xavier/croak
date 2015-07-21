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

import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;
import com.croak.croak.dao.CroakDAO;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class CroakDAOHibernateImpl implements CroakDAO {

  private static final Logger logger = Logger.getLogger(CroakDAOHibernateImpl.class);

  @Inject
  private Session session;

  @Override
  public List<Croak> getCroaks() {
    return session.createCriteria(Croak.class).list();
  }

  @Override
  public List<Croak> findCroaks(String query) throws CroakNotFoundException {
    return session.createCriteria(Croak.class)
                  .add(Restrictions.ilike("text", query, MatchMode.ANYWHERE))
                  .list();
  }

  @Override
  public List<Croak> getCroaksByUser(String username) throws UserNotFoundException {
    return session.createCriteria(Croak.class)
                  .createAlias("author", "a")
                  .add(Restrictions.eq("a.username", username))
                  .list();
  }

  @Override
  public List<Croak> getCroaksForUser(String username) throws UserNotFoundException {
    return session.createCriteria(Croak.class)
                  .createAlias("author", "a", Criteria.LEFT_JOIN)
                  .createAlias("a.followers", "fs", Criteria.LEFT_JOIN)
                  .add(Restrictions.disjunction()
                    .add(Restrictions.eq("a.username", username))
                    .add(Restrictions.eq("fs.username", username)))
                  .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                  .list();
  }

  @Override
  public Croak getCroak(Long id) throws CroakNotFoundException {
    return (Croak)session.createCriteria(Croak.class)
                  .add(Restrictions.idEq(id))
                  .uniqueResult();
  }

  @Override
  public Croak saveCroak(Croak croak) {
    session.persist(croak);
    return croak;
  }

  @Override
  public void removeCroak(Long id) throws CroakNotFoundException {
    session.delete(
      session.createCriteria(Croak.class)
             .add(Restrictions.eq("id", id))
             .uniqueResult());
  }
}
