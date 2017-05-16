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
	@Test
public void getId_sudentsInstantiateWithAnID_1() {
  assertTrue(testStudent.getId() > 0);
}

@Test
public void find_returnsStudentWithSameId_secondStudent() {
	Student firstStudent= new Student("malvin", 1);
	firstStudent.save();
  Student anotherStudent = new Student("cliffgor", 2);
	anotherStudent.save();
  assertEquals(Student.find(anotherStudent.getId()), anotherStudent);
}

@Test
	public void equals_returnsTrueIfNamesAretheSame() {
		Student firstStudent= new Student("clifgor", 1);
		firstStudent.save();
	  Student anotherStudent = new Student("clifgor", 1);
		anotherStudent.save();
		assertTrue(firstStudent.equals(anotherStudent));
	}

	@Test
  public void all_returnsAllInstancesOfStudent_true() {

    assertEquals(true, Student.all().get(0).equals(testStudent));
    assertEquals(true, Student.all().get(1).equals(secondStudent));
  }

	@Test
	public void save_assignsIdToObject() {
	  Student savedStudent = Student.all().get(0);
	  assertEquals(testStudent.getId(), savedStudent.getId());
	}

	@Test
  public void getId_studentsInstantiateWithAnID() {
    assertTrue(testStudent.getId() > 0);
  }
