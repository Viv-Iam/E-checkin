import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Group {
private String name;
private int id;

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

             public int getId() {
                 return id;
               }

               public List<Individual> getIndividuals() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM individuals where groupId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Individual.class);
          }
  }
               public static Group find(int id) {
                 try(Connection con = DB.sql2o.open()) {
                         String sql = "SELECT * FROM groups where id=:id";
                         Group group = con.createQuery(sql)
                           .addParameter("id", id)
                           .executeAndFetchFirst(Group.class);
                         return group;
                       }
             }

             @Override
               public boolean equals(Object otherGroup) {
                 if (!(otherGroup instanceof Group)) {
                      return false;
                    } else {
                      Group newGroup = (Group) otherGroup;
                      return this.getName().equals(newGroup.getName()) &&
                             this.getId() == newGroup.getId();
                 }
               }
               public void delete() {
                 try(Connection con = DB.sql2o.open()) {
                 String sql = "DELETE FROM groups WHERE id = :id;";
                 con.createQuery(sql)
                   .addParameter("id", this.id)
                   .executeUpdate();

               }

}
}
