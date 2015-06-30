package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.croak.croak.entities.User;

public class UserResourceImpl implements UserResource {

  private static final Logger logger = Logger.getLogger(UserResourceImpl.class);

  @Override
  public List<User> getUsers() {
    logger.info("* getUsers called");
    List<User> users = new ArrayList<User>();

    User u = new User("mustermann", "Max", "Mustermann");
    u.setPassword("123456");
    u.setEmail("unnamed@gmail.com");
    u.setAvatar("/img/mustermann.jpg");
    users.add(u);

    u = new User("BarackObama", "Barack", "Obama");
    u.setPassword("america");
    u.setEmail("obama@whitehouse.us");
    u.setAvatar("/img/barack-obama.jpg");
    users.add(u);

    return users;
  }

  @Override
  public User getUser(@PathParam("id") Long id) {
    logger.info("* Received id in 'getCroak': "+id);
    User u = null;

    if(id == 1) {
      u = new User("mustermann", "Max", "Mustermann");
      u.setPassword("123456");
      u.setEmail("unnamed@gmail.com");
      u.setAvatar("/img/mustermann.jpg");
    } else if(id == 2) {
      u = new User("BarackObama", "Barack", "Obama");
      u.setPassword("america");
      u.setEmail("obama@whitehouse.us");
      u.setAvatar("/img/barack-obama.jpg");
    }

    return u;
  }

  @Override
  public Response createUser(User user) {
    return null;
  }

  @Override
  public Response updateUser(User user) {
    return null;
  }
}
