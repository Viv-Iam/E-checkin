import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/e_checkin", "viv-iam", "password");
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteIndividualsQuery = "DELETE FROM individuals *;";
      String deleteGroupsQuery = "DELETE FROM groups *;";
      String deleteSignsQuery = "DELETE FROM signs *;";
      con.createQuery(deleteIndividualsQuery).executeUpdate();
      con.createQuery(deleteGroupsQuery).executeUpdate();
      con.createQuery(deleteSignsQuery).executeUpdate();
    }
  }


}
