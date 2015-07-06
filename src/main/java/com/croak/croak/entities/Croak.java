package com.croak.croak.entities;

import javax.persistence.*;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@JsonDeserialize(using = CroakDeserializer.class)
@JsonSerialize(using = CroakSerializer.class)
public class Croak {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @Column(nullable=false,length=140)
  private String text;

  @Column(nullable=false,length=7)
  private String color;

  @ManyToOne(optional=false)
  @JoinColumn(name="author")
  private User author;

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
    this.color = "#ffffff";
    this.author = author;
  }

  /**
   * Create a new instance and set the text, color and author
   * @param text body raw text
   * @param color background color for the croak
   * @param author user who wrote the croak
   */
  public Croak(final String text, final String color, final User author) {
    this.text = text;
    this.color = color;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getColor() {
    return color;
  }

  public User getAuthor() {
    return author;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setAuthor(User user) {
    this.author = author;
  }
}
