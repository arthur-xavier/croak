package com.croak.croak.dao;

import java.util.List;

import com.croak.croak.entities.Croak;
import com.croak.croak.exceptions.CroakNotFoundException;

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
   * Gets a list of croaks ordered by id, filtered by username
   * @return populated list of croaks created by @username
   */
  public List<Croak> getCroaksByUser(String username);

  /**
   * Get a croak based on its unique id.
   * @param id croak identifier (primary key)
   * @return populated croak found
   */
  public Croak getCroak(Long id);

  /**
   * Saves a croak. Handles both insert and update.
   * @param croak the croak to save
   * @return the persisted croak
   */
  public Croak saveCroak(Croak croak);

  /**
   * Removes a croak based on its unique id.
   * @param id croak identifier (primary key)
   */
  public void removeCroak(Long id) throws CroakNotFoundException;

  /**
   * Searches for a croak contining the words, hashtags or authors
   * stated in the query.
   * @param query string containing the words, hashtags or authors to search for
   * @return populated list of croaks found
   */
  public List<Croak> searchCroaks(String query);
}
