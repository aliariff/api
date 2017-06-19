package de.rwth.webtech.npe.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
public class Comment implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String author;
  private String message;
  private String timestamp;

  public Comment() {

  }

  public Comment(int id, String author, String message, String timestamp) {
    this.id = id;
    this.author = author;
    this.message = message;
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  @XmlElement
  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  @XmlElement
  public void setAuthor(String author) {
    this.author = author;
  }

  public String getMessage() {
    return message;
  }

  @XmlElement
  public void setMessage(String message) {
    this.message = message;
  }

  public String getTimestamp() {
    return timestamp;
  }

  @XmlElement
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Comment)) {
      return false;
    }
    Comment cmt = (Comment) obj;
    if (id == cmt.getId() && author.equals(cmt.getAuthor()) && message.equals(cmt.getMessage())
        && timestamp.equals(cmt.getTimestamp())) {
      return true;
    }
    return false;
  }
}
