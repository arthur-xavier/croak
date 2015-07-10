package com.croak.croak.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.croak.croak.entities.User;
import com.croak.croak.exceptions.UserNotFoundException;

/**
 * User Data Access Object interface.
 */
public interface UserDAO {

  /**
   * Gets a list of users ordered by id
   * @return populated list of users
   */
  public List<User> getUsers();

  /**
   * Searches for users whose username contains the provided String.
   * @param username string to search for in usernames
   * @return populated list of users found
   */
  public List<User> findUsers(String username) throws UserNotFoundException;

  /**
   * Get a user based on its unique username.
   * @param username user username (unique key)
   * @return populated user found
   */
  public User getUser(String username) throws UserNotFoundException;

  /**
   * Saves a user. Handles both insert and update.
   * @param user the user to save
   * @return the persisted user
   */
  @CommitAfter
  public User saveUser(User user);

  /**
   * Removes a user based on its unique username'.
   * @param username username (unique key)
   */
  @CommitAfter
  public void removeUser(String username) throws UserNotFoundException;

  /**
   * Makes the current logged in user follow another user.
   * Adds the specified user to the subscriptions list of the current user.
   * @param user the user to follow
   */
  @CommitAfter
  public void followUser(String username) throws UserNotFoundException;

  /**
   * Removes the specified user from the subscriptions list of the current user.
   * @param user the user to unfollow
   */
  @CommitAfter
  public void unfollowUser(String username) throws UserNotFoundException;
}
