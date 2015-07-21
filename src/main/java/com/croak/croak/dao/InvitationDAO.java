package com.croak.croak.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.croak.croak.entities.Invitation;
import com.croak.croak.exceptions.InvitationNotFoundException;
import com.croak.croak.exceptions.UserNotFoundException;

/**
 * Invitation Data Access Object interface.
 */
public interface InvitationDAO {

  /**
   * Gets a list of invitations
   * @return populated list of invitations
   */
  public List<Invitation> getInvitations();

  /**
   * Gets a list of invitations filtered by the sender
   * @return populated list of invitations made by @username
   */
  public List<Invitation> getInvitationsBySender(String username) throws UserNotFoundException;

  /**
   * Gets the list of invitations for user @username
   * @return populated list of invitations for @username
   */
  public List<Invitation> getInvitationsForUser(String username) throws UserNotFoundException;

  /**
   * Saves an invitation. Handles both insert and update.
   * @param invitation the invitation to save
   * @return the persisted invitation
   */
  @CommitAfter
  public Invitation saveInvitation(Invitation invitation);

  /**
   * Removes an invitation based on its unique id.
   * @param id invitation identifier (primary key)
   */
  @CommitAfter
  public void removeInvitation(Long id) throws InvitationNotFoundException;
}
