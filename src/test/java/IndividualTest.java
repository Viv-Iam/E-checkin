import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class IndividualTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Individual mIndividual;
  @Before
  public void instantiate() {
    mIndividual = new Individual("Vivian", 1);
  }

  @Test
  public void Individual_instantiatesCorrectly_true() {
    assertEquals(true, mIndividual instanceof Individual);
  }

  @Test
  public void Individual_instantiatesWithName_String() {
    assertEquals("Vivian", mIndividual.getName());
  }

  @Test
     public void all_returnsAllInstancesOfIndividual_true() {
       mIndividual.save();
       Individual secondIndividual = new Individual("Mike", 2);
       secondIndividual.save();
       assertEquals(true, Individual.all().get(0).equals(mIndividual));
       assertEquals(true, Individual.all().get(1).equals(secondIndividual));
     }

     @Test
public void clear_emptiesAllIndividualsFromArrayList_0() {
  assertEquals(Individual.all().size(), 0);
}

@Test
public void getId_individualsInstantiateWithAnID() {
  mIndividual.save();
  assertTrue(mIndividual.getId() > 0);
}

@Test
  public void find_returnsIndividualWithSameId_secondIndividual() {
    mIndividual.save();
    Individual secondIndividual = new Individual("Mike", 2);
    secondIndividual.save();
    assertEquals(Individual.find(secondIndividual.getId()), secondIndividual);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Individual secondIndividual = new Individual("Vivian", 1);
    assertTrue(mIndividual.equals(secondIndividual));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
    mIndividual.save();
    assertTrue(Individual.all().get(0).equals(mIndividual));
  }

  @Test
  public void save_assignsIdToObject() {
    mIndividual.save();
    Individual savedIndividual = Individual.all().get(0);
    assertEquals(mIndividual.getId(), savedIndividual.getId());
  }

  @Test
       public void save_savesGroupIdIntoDB_true() {
         Group myGroup = new Group("MC1");
         myGroup.save();
         Individual myIndividual = new Individual("Vivian", myGroup.getId());
         myIndividual.save();
         Individual savedIndividual = Individual.find(myIndividual.getId());
         assertEquals(savedIndividual.getGroupId(), myGroup.getId());
       }

       @Test
public void update_updatesIndividualName_true() {
 mIndividual.save();
 mIndividual.update("Samuel");
 assertEquals("Samuel", Individual.find(mIndividual.getId()).getName());
}

@Test
public void delete_deletesIndividual_true() {
  mIndividual.save();
  int myIndividualId = mIndividual.getId();
  mIndividual.delete();
  assertEquals(null, Individual.find(myIndividualId));
}
}
