Skip to content
This repository
Search
Pull requests
Issues
Gist
 @Dicksonmuli
 Sign out
 Watch 0
  Star 0
 Fork 0 paulinembabu/African-Kitenge
 Code  Issues 0  Pull requests 0  Projects 0  Wiki  Pulse  Graphs
Branch: master Find file Copy pathAfrican-Kitenge/src/main/java/User.java
c88eace  14 days ago
 paulinembabu Kitenge Ful Application
0 contributors
RawBlameHistory
121 lines (109 sloc)  3.27 KB
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class User {
  private String name;
  private String email;
  private String password;
  private String type;
  private int id;

  public static final String [] USER_TYPE = new String[] {"admin", "customer"};

  public User(String name, String email, String password, String type) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.type = type;
  }

  public String getName(){
    return name;
  }
  public String getEmail(){
    return email;
  }
  public String getPassword(){
    return password;
  }
  public String getType(){
    return type;
  }
  public int getId(){
    return id;
  }

  @Override
  public boolean equals(Object otherUser){
    if (!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return this.getName().equals(newUser.getName()) &&
             this.getEmail().equals(newUser.getEmail()) &&
             this.getPassword().equals(newUser.getPassword()) &&
             this.getType().equals(newUser.getType());
    }
  }
  public static List<User> all() {
    String sql = "SELECT * FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users (name, email, password, type) VALUES (admin, :email, moringa, :type)";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("email", this.email)
          .addParameter("type", this.type)
          .executeUpdate()
          .getKey();
    }
  }

  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users WHERE id=:id";
      User user = con.createQuery(sql)
        .addParameter("id", id)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(User.class);
        return user;
    }
  }
  public static User findLogin(String password) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users WHERE password=:password";
      User user = con.createQuery(sql)
        .addParameter("password", password)
        .throwOnMappingFailure(false)
        .executeAndFetchFirst(User.class);
        return user;
    }
  }

}
