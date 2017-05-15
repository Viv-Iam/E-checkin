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

  @Test
public void clear_emptiesAllGroupsFromList_0() {
  assertEquals(Group.all().size(), 0);
}

@Test
  public void getId_groupsInstantiateWithAnId_1() {
       mGroup.save();
       assertTrue(mGroup.getId() > 0);
  }

  @Test
public void find_returnsGroupWithSameId_secondGroup() {
  Group firstGroup = new Group("MC1");
     firstGroup.save();
     Group secondGroup = new Group("Prep");
     secondGroup.save();
     assertEquals(Group.find(secondGroup.getId()), secondGroup);
}

@Test
public void getIndividuals_initiallyReturnsEmptyList_ArrayList() {
  assertEquals(0, mGroup.getIndividuals().size());
}

// @Test
//  public void find_returnsNullWhenNoIndividFound_null() {
//    assertTrue(Category.find(999) == null);
//  }

@Test
      public void equals_returnsTrueIfNamesAretheSame() {
        Group firstGroup = new Group("MC1");
        Group secondGroup = new Group("Prep");
        assertTrue(firstGroup.equals(secondGroup));
      }

      @Test
            public void save_savesIntoDatabase_true() {
              mGroup.save();
              assertTrue(Group.all().get(0).equals(mGroup));
            }

            @Test
    public void save_assignsIdToObject() {
      mGroup.save();
      Group savedGroup = Group.all().get(0);
      assertEquals(mGroup.getId(), savedGroup.getId());
    }

    @Test
      public void getIndividuals_retrievesALlIndividualsFromDatabase_individualList() {
        mGroup.save();
        Individual firstIndividual = new Individual("Mimi", mGroup.getId());
        firstIndividual.save();
        Individual secondIndividual = new Individual("Sisi", mGroup.getId());
        secondIndividual.save();
        Individual[] individuals = new Individual[] { firstIndividual, secondIndividual };
        assertTrue(mGroup.getIndividuals().containsAll(Arrays.asList(individuals)));
      }


}
