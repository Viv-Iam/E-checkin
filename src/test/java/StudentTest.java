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

	// creating an instance of Student successfully
	@Test
	public void Student_instantiatesCorrectly_true() {
		assertTrue(testStudent instanceof Student);
	}
	// assigning each Student a name and then retrieve it
	@Test
	public void Student_instantiatesWithName_String() {
		assertEquals("brian", testStudent.getName());
	}
