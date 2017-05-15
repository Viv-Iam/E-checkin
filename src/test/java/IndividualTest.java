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
    mIndividual = new Individual("Vivian");
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
       firstIndividual.save();
       Individual secondIndividual = new Individual("Vivian", 2);
       secondIndividual.save();
       assertEquals(true, Individual.all().get(0).equals(firstIndividual));
       assertEquals(true, Individual.all().get(1).equals(secondIndividual));
     }

}
