package com.croak.croak.dao;

import java.util.List;

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
  public User saveUser(User user);

  /**
   * Removes a user based on its unique username'.
   * @param username username (unique key)
   */
  public void removeUser(String username) throws UserNotFoundException;
}
