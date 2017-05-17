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

      public int getId() {
    return id;
  }
  public int getGroupId() {
       return groupId;
     }

  public static Individual find(int id) {
try(Connection con = DB.sql2o.open()) {
  String sql = "SELECT * FROM individuals where id=:id";
  Individual individual = con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetchFirst(Individual.class);
  return individual;
}
}
//retrieves all the signs by student id
public List<Sign> getSigns() {
 try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM signs where studentId=:id";
    return con.createQuery(sql)
    .addParameter("id", this.id)
    .executeAndFetch(Sign.class);
    }
}
@Override
     public boolean equals(Object otherIndividual){
       if (!(otherIndividual instanceof Individual)) {
         return false;
       } else {
         Individual newIndividual = (Individual) otherIndividual;
         return this.getName().equals(newIndividual.getName()) &&
                this.getId() == newIndividual.getId() &&
                this.getGroupId() == newIndividual.getGroupId();
       }
     }

     public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO individuals(name, groupId) VALUES (:name, :groupId)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("groupId", this.groupId)
            .executeUpdate()
            .getKey();
        }
      }

      public void update(String name) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE individuals SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
  }
}
public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM individuals WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}

}
