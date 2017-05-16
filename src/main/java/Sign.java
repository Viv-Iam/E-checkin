import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.sql.Timestamp;

public  class Category {
	private int id;
	private Timestamp timein;
//contructor
	public Category(int studentId) {
		this.name = name;
		this.studentId = studentId;


		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO categories (timein, studentId) VALUES (now(), :studentId)";
			this.id = (int) con.createQuery(sql, true)
			 .addParameter("name", this.studentId)
			 .executeUpdate()
			 .getKey();
		}
	}
	//getter methods

		public String getStudentId() {
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

		@Override
	 public boolean equals(Object otherSign){
		 if (!(otherSign instanceof Sign)) {
			 return false;
		 } else {
			 Sign newSign = (Sign) otherSign;
			 return this.getPersonId() == newSign.getStudentId();
		 }
	 }
