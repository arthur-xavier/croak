package com.croak.croak.security;

import org.apache.tapestry5.ioc.annotations.Inject;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.croak.croak.entities.User;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.exceptions.UserNotFoundException;

public class UserRealm extends AuthorizingRealm {

  @Inject
  protected UserDAO userDao;

  public UserRealm() {
    setName("UserRealm");
    setCredentialsMatcher(new SimpleCredentialsMatcher());
  }

  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken)auth;
    try {
      User user = userDao.getUser(token.getUsername());
      return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    } catch(UserNotFoundException e) {
      return null;
    }
  }

  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String)principals.fromRealm(getName()).iterator().next();
    try {
      User user = userDao.getUser(username);
      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      info.addRole("user");
      return info;
    } catch(UserNotFoundException e) {
      return null;
    }
  }
}
