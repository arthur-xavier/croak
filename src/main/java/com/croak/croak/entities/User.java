package com.croak.croak.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@JsonSerialize(using = UserSerializer.class)
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable=false,length=50,unique=true)
  private String username;

  @Column(nullable=false)
  private String password;

  @Column(name="first_name",nullable=false,length=50)
  private String firstName;
  @Column(name="last_name",nullable=false,length=50)
  private String lastName;

  @Column(nullable=false,unique=true)
  private String email;
  private String avatar;
  private String quote;

  // set of users who User follows
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name="Friends", joinColumns = { @JoinColumn(name="subscriber") },inverseJoinColumns = @JoinColumn(name="follower"))
  private Set<User> subscriptions = new HashSet<User>();

  // set of users who follow User
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name="Friends", joinColumns = { @JoinColumn(name="follower") },inverseJoinColumns = @JoinColumn(name="subscriber"))
  private Set<User> followers = new HashSet<User>();

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

  /**
   * Create a new instance and set the username, first and last names
   * @param username login name for user
   * @param firstName user's first name
   * @param lastName user's last name
   */
  public User(final String username, final String firstName, final String lastName) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Create a new instance and set the username, first and last names and avatar
   * @param username login name for user
   * @param firstName user's first name
   * @param lastName user's last name
   * @param avatar user's avatar url
   */
  public User(final String username, final String firstName, final String lastName, final String avatar) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatar = avatar;
  }

  /**
   * Create a new instance and set the id, username, first and last names and avatar
   * @param id primary key identification number for user
   * @param username login name for user
   * @param firstName user's first name
   * @param lastName user's last name
   * @param avatar user's avatar url
   */
  public User(final Long id, final String username, final String firstName, final String lastName, final String avatar) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatar = avatar;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }
  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getQuote() {
    return quote;
  }

  public Set<User> getSubscriptions() {
    return this.subscriptions;
  }
  public Set<User> getFollowers() {
    return this.followers;
  }

  public void addSubscription(User subscription) {
      getSubscriptions().add(subscription);
  }
  public void addFollower(User follower) {
      getFollowers().add(follower);
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

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  public void setSubscriptions(Set<User> subscriptions) {
    this.subscriptions = subscriptions;
  }
  public void setFollowers(Set<User> followers) {
    this.followers = followers;
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
  public String toString() {
    return this.username;
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return (username != null ? username.hashCode() : 0);
  }
}
