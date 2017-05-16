import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Student {
	private String name;
	private int id;
	private int categoryId;

	public Student(String name, int categoryId) {
			this.name = name;
			this.categoryId = categoryId;

      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO students(name, categoryId) VALUES (:name, :categoryId)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .addParameter("categoryId", this.categoryId)
          .executeUpdate()
          .getKey();
      }
	}
