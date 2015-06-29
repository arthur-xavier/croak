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

	@Override
	public List<Croak> getCroaks() {
		logger.info("* getCroaks called");
		List<Croak> croaks = new ArrayList<Croak>();
    croaks.add(new Croak("Test croak #imCroaking", "#fffde7", new User("unnamed", "No", "Name")));
    croaks.add(new Croak("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vestibulum magna orci aliquam. #lipsum #loremIpsum", "#e1f5fe", new User("BarackObama", "Barack", "Obama")));
		return croaks;
	}

	@Override
	public Croak getCroak(@PathParam("id") Long id) {
		logger.info("* Received id in 'getCroak': "+id);
    if(id == 1) {
      return new Croak("Test croak #imCroaking", "#fffde7", new User("unnamed", "No", "Name"));
    } else if(id == 2) {
      return new Croak("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vestibulum magna orci aliquam. #lipsum #loremIpsum", "#e1f5fe", new User("BarackObama", "Barack", "Obama"));
    }

		return null;
	}
}
