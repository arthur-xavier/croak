package com.croak.croak.components;

import java.util.HashSet;
import java.util.Set;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

import org.apache.shiro.SecurityUtils;

import com.croak.croak.entities.Invitation;
import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;
import com.croak.croak.dao.CroakDAO;
import com.croak.croak.dao.InvitationDAO;
import com.croak.croak.dao.UserDAO;
import com.croak.croak.pages.Friends;
import com.croak.croak.pages.Home;

public class UserProfile {

  @Inject
  private UserDAO userDao;
  @Inject
  private CroakDAO croakDao;
  @Inject
  private InvitationDAO invitationDAO;

  @Parameter(required = true)
  @Property
  private User user;

  @Property
  private int followersNumber;
  @Property
  private int followingNumber;
  @Property
  private int croaksNumber;

  @Property
  private boolean follows;
  @Property
  private boolean followsYou;
  @Property
  private boolean self;

  public Object onActionFromFollow(String username) throws UserNotFoundException {
    userDao.followUser(username);
    return Friends.class;
  }

  public Object onActionFromUnfollow(String username) throws UserNotFoundException {
    userDao.unfollowUser(username);
    return Friends.class;
  }

  public Object onActionFromInvite(String username) throws UserNotFoundException {
    Invitation invitation = new Invitation(
      userDao.getUser((String)SecurityUtils.getSubject().getPrincipal()),
      userDao.getUser(username)
    );
    invitationDAO.saveInvitation(invitation);
    return Home.class;
  }

  @BeginRender
  public void beginRender() throws UserNotFoundException {
    this.followingNumber = this.user.getSubscriptions().size();

    User theUser = userDao.getUser((String)SecurityUtils.getSubject().getPrincipal());
    this.follows = this.user.getFollowers().contains(theUser);
    this.followsYou = this.user.getSubscriptions().contains(theUser);
    this.self = this.user.getUsername().equals((String)SecurityUtils.getSubject().getPrincipal());

    Set followers = new HashSet<User>();
    for(User u : userDao.getUsers()) {
      if(u.getSubscriptions().contains(this.user)) {
        followers.add(u);
      }
    }
    this.followersNumber = followers.size();

    this.croaksNumber = croakDao.getCroaksByUser(this.user.getUsername()).size();
  }
}
