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
  public void getName_groupInstantiatesWithName_MC1() {
    assertEquals("MC1", mGroup.getName());
  }

  @Test
  public void all_returnsAllInstancesOfGroup_true() {
    Group firstGroup = new Group("MC1");
          firstGroup.save();
          Group secondGroup = new Group("Prep");
          secondGroup.save();
          assertEquals(true, Group.all().get(0).equals(firstGroup));
          assertEquals(true, Group.all().get(1).equals(secondGroup));
  }

  

}
