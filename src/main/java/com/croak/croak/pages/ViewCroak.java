package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.routing.annotations.At;

import com.croak.croak.entities.Croak;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.rest.CroakResource;

public class ViewCroak {

  @Inject
  private CroakResource croakResource;

  @Property
  private Croak croak;

  public void onActivate(Long id) throws CroakNotFoundException {
    this.croak = croakResource.getCroak(id);
  }
}
