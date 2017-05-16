import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;


public class SignTest {
	private Sign testSign;
  @Rule
  public DatabaseRule database = new DatabaseRule();
	// instantiates a sign in object before every test
	@Before
	public void instantiate() {
		testSign = new Sign(1);
	}
  @Test
  public void Sign_instantiatesCorrectly_true() {
    assertTrue(testSign instanceof Sign);
  }
	//overriding equals
	@Test
	public void equals_returnsTrueIfStudentIdAreSame_true(){
		Sign testSign = new Sign(1);
		Sign anotherSign = new Sign(1);
		assertTrue(testSign.equals(anotherSign));
	}
	//instantiates with studentId
	@Test
	public void getStudentId_returnsStudentId() {
		assertEquals(1, testSign.getStudentId());
	}
	//save assigns an id
	@Test
  public void save_assignsIdToSign() {
    Sign savedSign = Sign.all().get(0);
    assertEquals(savedSign.getId(), testSign.getId());
  }
}
