package com.croak.croak.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAOImpl;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

public class CroakDAOImpl implements CroakDAO {

  private Map<Long, Croak> croaks = new HashMap<Long, Croak>();
  private Long id = 0L;

  public CroakDAOImpl() {
    this.saveCroak(new Croak("Test croak #imCroaking", "#fffde7", new User("mustermann", "Max", "Mustermann", "/img/mustermann.jpg")));
    this.saveCroak(new Croak("Here's what I said yesterday: We are blessed with the most beautiful God-given landscape in the entire world... We have to be good stewards for it.", "#f3e5f5", new User("BarackObama", "Barack", "Obama", "/img/barack-obama.jpg")));
    this.saveCroak(new Croak("Video fast fertig geschnitten & internet hier ist grad nice, kommt also gleich online :)", "#f1f8e9", new User("unge", "Simon", "Unge", "/img/simon-unge.jpg")));
    this.saveCroak(new Croak("on some real young trill shit doe. I miss y'all. #Hooligans #Love #Peace #TacoBell #GameOfThrones #Hashtag #HashBrowns", "#fafafa", new User("EyeOfJackieChan", "Jackie", "Chan", "/img/jackie-chan.jpg")));
    this.saveCroak(new Croak("Hey you! Out there in the cold, getting lonely getting old, can you feel me?", "#fffde7", new User("rogerwaters", "Roger", "Waters", "/img/roger-waters.jpg")));
    this.saveCroak(new Croak("I think I'm addicted to #croaking #cantstop", "#e1f5fe", new User("BillGates", "Bill", "Gates", "/img/bill-gates.jpg")));
    this.saveCroak(new Croak("Did you know that croaking is awesome? I really think that you all should try it! #croaking #awesome", "#fff3e0", new User("JackDawson_pa", "Jack", "Dawson", "/img/jack-dawson.jpg")));
  }

  public List<Croak> getCroaks() {
    return new ArrayList<Croak>(croaks.values());
  }

  @Override
  public List<Croak> findCroaks(String query) throws CroakNotFoundException {
    List<Croak> cs = new ArrayList<Croak>();
    for(Map.Entry<Long, Croak> entry : croaks.entrySet()) {
      if(entry.getValue().getText().toLowerCase().contains(query)) {
        cs.add(entry.getValue());
      }
    }
    if(cs.size() == 0) {
      throw new CroakNotFoundException("No croak found containing " + query);
    }
    return cs;
  }

  public List<Croak> getCroaksByUser(String username) throws UserNotFoundException {
    List<Croak> cs = new ArrayList<Croak>();
    for(Map.Entry<Long, Croak> entry : croaks.entrySet()) {
      if(entry.getValue().getAuthor().getUsername().equals(username)) {
        cs.add(entry.getValue());
      }
    }
    return cs;
  }

  public List<Croak> getCroaksForUser(String username) throws UserNotFoundException {
    List<Croak> cs = new ArrayList<Croak>();
    User user = (new UserDAOImpl()).getUser(username);
    for(Map.Entry<Long, Croak> entry : croaks.entrySet()) {
      if(entry.getValue().getAuthor().getUsername().equals(username)
          || user.getSubscriptions().contains(entry.getValue().getAuthor())) {
        cs.add(entry.getValue());
      }
    }
    return cs;
  }

  public Croak getCroak(Long id) throws CroakNotFoundException {
    Croak croak = croaks.get(id);
    if(croak == null)
      throw new CroakNotFoundException("Croak with id " + id + " not found.");
    return croak;
  }

  public Croak saveCroak(Croak croak) {
    croak.setId(this.id++);
    croaks.put(croak.getId(), croak);
    return croak;
  }

  public void removeCroak(Long id) throws CroakNotFoundException {
    if(croaks.remove(id) == null)
      throw new CroakNotFoundException("Croak with id '" + id + "' not found.");
  }

  public List<Croak> searchCroaks(String query) {
    // TODO: implement com.croak.croak.dao.CroakDAOImpl.searchCroaks
    return null;
  }
}
