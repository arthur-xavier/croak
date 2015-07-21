package com.croak.croak.entities;

import javax.persistence.*;

@Entity
public class Invitation {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @ManyToOne(optional=false)
  @JoinColumn(name="sender")
  private User sender;

  @ManyToOne(optional=false)
  @JoinColumn(name="user")
  private User user;

  /**
   * Default constructor
   */
  public Invitation() {}

  /**
   * Create a new instance and set the sender and receiver
   * @param sender User who sent the invitation
   * @param user   User who will receive the invitation
   */
  public Invitation(final User sender, final User user) {
    this.sender = sender;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public User getSender() {
    return sender;
  }

  public User getUser() {
    return user;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setAuthor(User sender) {
    this.sender = sender;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
