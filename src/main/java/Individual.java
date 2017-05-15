import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Individual {
private String name;
private int id;
private int groupId;

public Individual(String name, int groupId) {
    this.name = name;
    this.groupId = groupId;

}

public String getName() {
    return name;
  }

  public static List<Individual> all() {
        String sql = "SELECT id, name, groupId FROM individuals";
        try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Individual.class);
        }
      }
}
