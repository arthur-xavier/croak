package com.croak.croak.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

  @Id
  private long id;
  private String username;    // required, unique
  private String password;    // required
  private String firstName;   // required
  private String lastName;    // required
  private String email;       // required, unique

  // set of users who User follows
  private Set<User> subscriptions = new HashSet<User>();

  /**
   * Default constructor
   */
  public User() {}

  /**
   * Create a new instance and set the username
   * @param username login name for user
   */
  public User(final String username) {
    this.username = username;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  @Column(nullable=false,length=50,unique=true)
  public String getUsername() {
    return username;
  }
  @Column(nullable=false)
  public String getPassword() {
    return password;
  }

  @Column(name="first_name",nullable=false,length=50)
  public String getFirstName() {
    return firstName;
  }
  @Column(name="last_name",nullable=false,length=50)
  public String getLastName() {
    return lastName;
  }

  @Column(nullable=false,unique=true)
  public String getEmail() {
    return email;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="friends",joinColumns = { @JoinColumn(name="subscriber") },inverseJoinColumns = @JoinColumn(name="follower"))
  public Set<Role> getSubscriptions() {
    return subscription;
  }

  /**
   * Adds a subcription for the user
   * @param subscription the fully instantiated User to whom U subscribed
   */
  public void addSubscription(User subscription) {
      getSubscriptions().add(subscription);
  }


  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof User)) {
      return false;
    }
    final User user = (User)o;
    return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return (username != null ? username.hashCode() : 0);
  }
}
