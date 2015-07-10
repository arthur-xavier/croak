package com.croak.croak.pages;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.beaneditor.Validate;

import org.apache.shiro.SecurityUtils;

public class Logout {
  Object onActivate() {
    SecurityUtils.getSubject().logout();
    return Login.class;
  }
}
