package com.croak.croak.exceptions;

public class CroakNotFoundException extends Exception {

  public CroakNotFoundException() {}

  public CroakNotFoundException(String message) {
    super(message);
  }

  public CroakNotFoundException(Throwable cause) {
    super(cause);
  }

  public CroakNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
