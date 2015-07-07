package com.croak.croak.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.croak.croak.entities.Croak;
import com.croak.croak.exceptions.CroakNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

/**
 * Croak Data Access Object interface.
 */
public interface CroakDAO {

  /**
   * Gets a list of croaks ordered by id (semantically: order of creation)
   * @return populated list of croaks
   */
  public List<Croak> getCroaks();

  /**
   * Searches for croaks whose text contains the provided String.
   * @param query string to search for in croaks
   * @return populated list of croaks found
   */
  public List<Croak> findCroaks(String query) throws CroakNotFoundException;

  /**
   * Gets a list of croaks ordered by id, filtered by username
   * @return populated list of croaks created by @username
   */
  public List<Croak> getCroaksByUser(String username) throws UserNotFoundException;

  /**
   * Gets the list of croaks ordered by id, created by users who @username follows
   * @return populated list of croaks @username can see
   */
  public List<Croak> getCroaksForUser(String username) throws UserNotFoundException;

  /**
   * Get a croak based on its unique id.
   * @param id croak identifier (primary key)
   * @return populated croak found
   */
  public Croak getCroak(Long id) throws CroakNotFoundException;

  /**
   * Saves a croak. Handles both insert and update.
   * @param croak the croak to save
   * @return the persisted croak
   */
  @CommitAfter
  public Croak saveCroak(Croak croak);

  /**
   * Removes a croak based on its unique id.
   * @param id croak identifier (primary key)
   */
  @CommitAfter
  public void removeCroak(Long id) throws CroakNotFoundException;
}
