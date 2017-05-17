import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;


public class SignTest {
	private Sign testSign;
	private Sign secondSign;
  @Rule
  public DatabaseRule database = new DatabaseRule();
	// instantiates a sign in object before every test
	@Before
	public void instantiate() {
		testSign = new Sign(1);
		secondSign = new Sign(2);
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
	@Test
  public void all_returnsAllInstancesOfSign_true() {

    assertTrue( Sign.all().get(0).equals(testSign));
    assertTrue( Sign.all().get(1).equals(secondSign));
  }
	//returns a sign with given id
	@Test
 public void find_returnsSignWithSameId_fourthSign() {
	 Sign thirdSign = new Sign(3);
	 Sign fourthSign = new Sign(4);
	 assertEquals(Sign.find(fourthSign.getId()), fourthSign);
 }
 // save method saves StudentId in the DB
 @Test
 public void save_savesStudentIdIntoDB_true() {
 Individual testIndividual = new Individual("Vivian", 1);
  testIndividual.save();
 Sign anotherSign = new Sign(testIndividual.getId());
 anotherSign.save();
 Sign savedSign = Sign.find(anotherSign.getId());
 assertEquals(savedSign.getStudentId(), testIndividual.getId());
 }

 //saving time when a student signs in
 @Test
 public void save_recordsTimeOfSignInDatabase() {
	 testSign.save();
	 Timestamp savedSignin = Sign.find(testSign.getId()).getTimein();
	 Timestamp rightNow = new Timestamp(new Date().getTime());
	 assertEquals(rightNow.getDay(), savedSignin.getDay());
 }

}
