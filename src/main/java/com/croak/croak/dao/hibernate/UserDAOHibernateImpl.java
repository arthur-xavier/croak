package com.croak.croak.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import org.apache.shiro.SecurityUtils;

import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.exceptions.UserNotFoundException;

public class UserDAOHibernateImpl implements UserDAO {

  @Inject
  private Session session;

  @Override
  public List<User> getUsers() {
    return session.createCriteria(User.class).list();
  }

  @Override
  public List<User> findUsers(String username) throws UserNotFoundException {
  return session.createCriteria(User.class)
                .add(Restrictions.ilike("username", username, MatchMode.START))
                .list();
  }

  @Override
  public User getUser(String username) throws UserNotFoundException {
    return (User)session.createCriteria(User.class)
                        .add(Restrictions.eq("username", username))
                        .uniqueResult();
  }

  @Override
  public User saveUser(User user) {
    session.persist(user);
    return null;
  }

  @Override
  public void removeUser(String username) throws UserNotFoundException {
    User user = (User)session.createCriteria(User.class)
                        .add(Restrictions.eq("username", username))
                        .uniqueResult();
    session.delete(user);
  }

  @Override
  public void followUser(String username) throws UserNotFoundException {
    User user = getUser((String)SecurityUtils.getSubject().getPrincipal());
    User subscription = getUser(username);
    user.addSubscription(subscription);
    session.persist(user);
  }

  @Override
  public void unfollowUser(String username) throws UserNotFoundException {
    User user = getUser((String)SecurityUtils.getSubject().getPrincipal());
    User subscription = getUser(username);
    user.removeSubscription(subscription);
    subscription.removeFollower(user);
    session.persist(user);
  }
}
