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
	public String getName() {
	    return name;
	  }

	  public int getId() {
	    return id;
	  }

		public int getCategoryId() {
        return categoryId;
      }

		public static Student find(int id) {
			  try(Connection con = DB.sql2o.open()) {
			    String sql = "SELECT * FROM students where id=:id";
			    Student student = con.createQuery(sql)
			      .addParameter("id", id)
			      .executeAndFetchFirst(Student.class);
			    return student;
			  }
			}
			public static List<Student> all() {
    String sql = "SELECT id, name, categoryId FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

	public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students(name, categoryId) VALUES (:name, :categoryId)";
			this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
				.addParameter("categoryId", this.categoryId)
        .executeUpdate()
        .getKey();
    }
  }
	@Override
	public boolean equals(Object otherStudent) {
		if (!(otherStudent instanceof Student)) {
			return false;
		} else {
			Student newStudent = (Student) otherStudent;
			return this.getName().equals(newStudent.getName())  && this.getCategoryId() == newStudent.getCategoryId();
		}
		}
		public void update(String name) {
	  try(Connection con = DB.sql2o.open()) {
	    String sql = "UPDATE students SET name = :name WHERE id = :id";
	    con.createQuery(sql)
	      .addParameter("name", name)
	      .addParameter("id", id)
	      .executeUpdate();
	  }
	}
	public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM students WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}


}
