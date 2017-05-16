import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.sql.Timestamp;

public  class Category {
	private String name;
	private int id;
	private Timestamp timein;
//contructor
	public Category(String name, int studentId) {
		this.name = name;
		this.studentId = studentId;

		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO categories (name) VALUES (:name)";
			this.id = (int) con.createQuery(sql, true)
			 .addParameter("name", this.name)
			 .executeUpdate()
			 .getKey();
		}
	}
	//getter methods

		public String getName() {
			 return name;
		 }

		 public int getId() {
			 return id;
		 }
		 // public List<Student> getStudent() {
 		// 	try(Connection con = DB.sql2o.open()) {
     //     String sql = "SELECT * FROM student where categoryId=:id";
     //     return con.createQuery(sql).addParameter("id", this.id).executeAndFetch(Student.class);
     //     }
 		// }
