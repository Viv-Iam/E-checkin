
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class User {
	private String name;
	private String password;
  private String type;
  private int id;

  public static final String [] USER_TYPE = new String[] {"admin", "student"};
	public static final String USER_NAME = "user";
	public static final String USER_PASSWORD = "moringa";

  public User(String name, String password, String type) {
    this.name = name;
    this.password = password;
    this.type = type;
  }

  public String getName(){
    return name;
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
      String sql = "INSERT INTO users (name, password, type) VALUES (:name, :password, :type)";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("type", this.type)
					.addParameter("name", this.name)
					.addParameter("password", this.password)
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
