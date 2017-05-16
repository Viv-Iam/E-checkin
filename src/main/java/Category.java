import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public  class Category {
	private String name;
	private int id;
//contructor
	public Category(String name) {
		this.name = name;

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
