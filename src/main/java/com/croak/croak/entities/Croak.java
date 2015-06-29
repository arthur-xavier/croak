package com.croak.croak.entities;

import javax.persistence.*;

@Entity
public class Croak {

  @Id
  private long id;
  private String text;  // required
  private User author;  // required

  /**
   * Default constructor
   */
  public Croak() {}

  /**
   * Create a new instance and set the text and author
   * @param text body raw text
   * @param author user who wrote the croak
   */
  public Croak(final String text, final User author) {
    this.text = text;
    this.author = author;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  @Column(nullable=false,length=150)
  public String getText() {
    return text;
  }

  @Column(nullable=false)
  public User getAuthor() {
    return author;
  }


  public void setText(String text) {
    this.text = text;
  }
  
  public void setAuthor(User user) {
    this.author = author;
  }
}
