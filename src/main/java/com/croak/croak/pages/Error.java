package com.croak.croak.pages;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ExceptionReporter;

/**
 * Customized errror handling page
 */
public class Error implements ExceptionReporter {

  @Property
  @Persist(PersistenceConstants.FLASH)
  private String error;

  public void reportException(Throwable exception) {
    error = exception.getLocalizedMessage();
  }
}
