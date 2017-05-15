import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Group {
private String name;

public Group(String name) {
      this.name = name;
  }

public String getName() {
    return name;
  }

public static List<Group> all() {
    String sql = "SELECT id, name FROM groups";
      try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Group.class);
      }
      }

       public void save() {
             try(Connection con = DB.sql2o.open()) {
               String sql = "INSERT INTO groups(name) VALUES (:name)";
               this.id = (int) con.createQuery(sql, true)
                 .addParameter("name", this.name)
                 .executeUpdate()
                 .getKey();
               }
             }

}
