import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GroupTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Group mGroup;
  @Before
  public void instantiate() {
    mGroup = new Group("MC1");
  }

  @Test
  public void group_instantiatesCorrectly_true() {
  assertEquals(true, mGroup instanceof Group);
  }

  @Test
  public void getName_categoryInstantiatesWithName_MC1() {
    assertEquals("MC1", testCategory.getName());
  }

}
