package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import com.croak.croak.entities.Croak;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.dao.CroakDAO;

@RequiresAuthentication
public class ViewCroak {

  @Inject
  private CroakDAO croakDao;

  @Property
  private Croak croak;

  public void onActivate(Long id) throws CroakNotFoundException {
    this.croak = croakDao.getCroak(id);
  }
}
