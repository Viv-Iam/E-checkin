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
  public void find_returnsTaskWithSameId_secondTask() {
    mIndividual.save();
    Individual secondIndividual = new Individual("Mike", 2);
    secondIndividual.save();
    assertEquals(Individual.find(secondIndividual.getId()), secondIndividual);
  }

}
