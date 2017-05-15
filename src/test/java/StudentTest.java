import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StudentTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();

  private Student testStudent;
  private Student secondStudent;
  //instantiating an object
  @Before
  public void instantiate() {
    testStudent = new Student("brian", 1);
    secondStudent = new Student("Mow the lawn", 1);
  }
