package com.croak.croak.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;

public class UserDAOImpl implements UserDAO {

  private Map<String, User> users = new HashMap<String, User>();

  public UserDAOImpl() {
    User mustermann = new User("mustermann", "Max", "Mustermann", "img/mustermann.jpg");
    mustermann.setQuote("Totally awesome!");
    User obama      = new User("BarackObama", "Barack", "Obama", "img/barack-obama.jpg");
    obama.setQuote("Yes we can!");
    User gates      = new User("BillGates", "Bill", "Gates", "img/bill-gates.jpg");
    gates.setQuote("See? I'm the richest!");
    User dawson     = new User("JackDawson_pa", "Jack", "Dawson", "img/jack-dawson.jpg");
    dawson.setQuote("Jack Dawson loves my big sphinx of quartz");
    User chan       = new User("EyeOfJackieChan", "Jackie", "Chan", "img/jackie-chan.jpg");
    User waters     = new User("rogerwaters", "Roger", "Waters", "img/roger-waters.jpg");
    User unge       = new User("unge", "Simon", "Unge", "img/simon-unge.jpg");

    mustermann.addSubscription(obama);
    mustermann.addSubscription(gates);
    mustermann.addSubscription(dawson);
    mustermann.addSubscription(chan);
    mustermann.addSubscription(waters);
    //mustermann.addSubscription(unge);

    dawson.addSubscription(mustermann);
    chan.addSubscription(mustermann);
    unge.addSubscription(mustermann);

    this.saveUser(mustermann);
    this.saveUser(obama);
    this.saveUser(gates);
    this.saveUser(dawson);
    this.saveUser(chan);
    this.saveUser(waters);
    this.saveUser(unge);
  }

  @Override
  public List<User> getUsers() {
    return new ArrayList<User>(users.values());
  }

  @Override
  public User getUser(String username) {
    return users.get(username);
  }

  @Override
  public User saveUser(User user) {
    users.put(user.getUsername(), user);
    return user;
  }

  @Override
  public void removeUser(String username) throws UserNotFoundException {
    if(users.remove(username) == null)
      throw new UserNotFoundException("User with username @" + username + " not found.");
  }
}
