package gr.ntua.ece.softeng20b.backend.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, length = 32, nullable = false)
  private String name;

  User() {}

  public User(String name) {
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User u = (User) o;
    return Objects.equals(this.id, u.id) && Objects.equals(this.name, u.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + this.id + ", name='" + this.name + "'}";
  }
}
