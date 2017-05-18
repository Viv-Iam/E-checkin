import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserTest {
  User testUser = new User("user", "moringa", "admin");

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void user_instantiatesCorrectly_true(){
    User testUser = new User("user", "moringa", "admin");
    assertEquals(true, testUser instanceof User);
  }
  @Test
  public void user_testForGetterMethods_3(){
    assertThat(testUser.getName(), is(equalTo("user")));
    assertThat(testUser.getPassword(), is(equalTo("moringa")));
    assertThat(testUser.getType(), is(equalTo("admin")));
  }
  @Test
  public void equals_returnsTrueIfNameandPasswordAreSame_true(){
      User testUser = new User("user", "moringa", "admin");
      User anotherUser = new User( "user", "moringa", "admin");
      assertTrue(testUser.equals(anotherUser));
  }
  @Test
  public void save_insertsObjectIntoDatabase_User(){
    testUser.save();
    assertEquals(true, User.all().get(0).equals(testUser));
  }
  @Test
  public void save_assignsIdToUser() {
    User testUser1 = new User("user", "moringa", "admin");
    testUser1.save();
    User savedUser = User.all().get(0);
    assertEquals(savedUser.getId(), testUser1.getId());
  }
  @Test
  public void all_returnsAllUsers_true(){
    User testUser1 = new User("user", "moringa", "admin");
    testUser1.save();
    User testUser2 = new User("user", "customer", "admin");
    testUser2.save();
    assertEquals(true, User.all().get(0).equals(testUser1));
    assertEquals(true, User.all().get(1).equals(testUser2));
  }
  @Test
  public void find_returnsUserWithSameId_secondUser(){
    User testUser1 = new User( "user", "moringa", "admin");
    testUser1.save();
    User testUser2 = new User( "user", "customer", "admin");
    testUser2.save();
    assertEquals(User.find(testUser2.getId()), testUser2);
  }



}
