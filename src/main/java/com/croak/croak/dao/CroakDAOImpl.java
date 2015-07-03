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
    this.saveCroak(new Croak("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vestibulum magna orci aliquam. #lipsum #loremIpsum", "#e1f5fe", new User("BarackObama", "Barack", "Obama", "/img/barack-obama.jpg")));
    this.saveCroak(new Croak("Das hier ist nur noch irgendein croak auf #Deutsch", "#fafafa", new User("unge", "Simon", "Unge", "/img/simon-unge.jpg")));
  }

  public List<Croak> getCroaks() {
    return new ArrayList<Croak>(croaks.values());
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

  public Croak getCroak(Long id) {
    return croaks.get(id);
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
