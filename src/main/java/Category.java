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
		 // public List<Student> getStudent() {
 		// 	try(Connection con = DB.sql2o.open()) {
     //     String sql = "SELECT * FROM student where categoryId=:id";
     //     return con.createQuery(sql).addParameter("id", this.id).executeAndFetch(Student.class);
     //     }
 		// }

 		 public static Category find(int id) {
 			 try(Connection con = DB.sql2o.open()) {
           String sql = "SELECT * FROM categories where id=:id";
           Category category = con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetchFirst(Category.class);
           return category;
         }
 		}
		//returns all the categories
		public static List<Category> all() {
			 String sql = "SELECT id, name FROM categories";
			 try(Connection con = DB.sql2o.open()) {
				 return con.createQuery(sql).executeAndFetch(Category.class);
			 }
		 }
		//  saves a categiry
		 public void save() {
         try(Connection con = DB.sql2o.open()) {
           String sql = "INSERT INTO categories (name) VALUES (:name)";
					 this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
         }
    }
		//overriding equals
		@Override
		 public boolean equals(Object otherCategory) {
			 if (!(otherCategory instanceof Category)) {
				 return false;
			 } else {
				 Category newCategory = (Category) otherCategory;
				 return this.getName().equals(newCategory.getName()) ;
			 }
}
//deletes the category
public void delete() {
	try(Connection con = DB.sql2o.open()) {
	String sql = "DELETE FROM categories WHERE id = :id;";
	con.createQuery(sql)
		.addParameter("id", this.id)
		.executeUpdate();
	}
}
//updating a category
public void update(String name) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE categories SET (name) = (:name) WHERE id=:id";
			 con.createQuery(sql)
			 .addParameter("name", name)
			 .addParameter("id", id)
			 .executeUpdate();
		}
}
}
