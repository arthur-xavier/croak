package com.croak.croak.exceptions;

public class InvitationNotFoundException extends Exception {

  public InvitationNotFoundException() {}

  public InvitationNotFoundException(String message) {
    super(message);
  }

  public InvitationNotFoundException(Throwable cause) {
    super(cause);
  }

  public InvitationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
