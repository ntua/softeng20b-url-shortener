package gr.ntua.ece.softeng20b.backend.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Url {
  @Id
  @Column(length = 8)
  private String id;

  @Column(nullable = false)
  private String target;

  @Column(nullable = false)
  private Integer used;

  @ManyToOne
  private User user;

  Url() {}

  Url(String target, User user) {
    this.target = target;
    this.user = user;
    this.used = 0;
    this.id = Url.encode(this.hashCode());
  }

  public void initialize() {
    this.used = 0;
    this.id = Url.encode(this.hashCode());
  }

  public String getId() {
    return this.id;
  }

  public String getTarget() {
    return this.target;
  }

  public Integer getUsed() {
    return this.used;
  }

  public User getUser() {
    return this.user;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

  public void incUsed() {
    ++this.used;
  }

  public static String encode(int h) {
    String alphabet =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ-abcdefghijklmnopqrstuvwxyz_0123456789";
    long r = h < 0 ? h + (1L << 32) : h;
    StringBuilder b = new StringBuilder(6);
    for (int i = 0; i < 6; ++i) {
      b.append(alphabet.charAt((int) (r & 63L)));
      r >>= 6;
    }
    return b.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Url)) return false;
    Url u = (Url) o;
    return Objects.equals(this.id, u.id)
        && Objects.equals(this.target, u.target)
        && Objects.equals(this.user, u.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.target, this.user);
  }

  @Override
  public String toString() {
    return "Url{" + "id='" + this.id + "', target='" + this.target +
           "', user=" + this.user + ", used=" + this.used + "}";
  }

}
