package com.croak.croak.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import com.croak.croak.entities.Croak;
import com.croak.croak.entities.User;

public class CroakResourceImpl implements CroakResource {

  private static final Logger logger = Logger.getLogger(CroakResourceImpl.class);

  private static List<Croak> croaks = new ArrayList<Croak>();

  public CroakResourceImpl() {
    croaks.add(new Croak("Test croak #imCroaking", "#fffde7", new User("mustermann", "Max", "Mustermann", "img/mustermann.jpg")));
    croaks.add(new Croak("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vestibulum magna orci aliquam. #lipsum #loremIpsum", "#e1f5fe", new User("BarackObama", "Barack", "Obama", "img/barack-obama.jpg")));
  }

  @Override
  public List<Croak> getCroaks() {
    logger.info("* getCroaks called");
    return croaks;
  }

  @Override
  public Croak getCroak(@PathParam("id") Long id) {
    logger.info("* Received id in getCroak: "+id);
    return croaks.get(id.intValue());
  }

  @Override
  public Response createCroak(Croak croak) {
    logger.info("* createCroak called");
    croaks.add(croak);
    return Response.ok().build();
  }

  @Override
  public Response updateCroak(Croak croak) {
    return null;
  }
}
