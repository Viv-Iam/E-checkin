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

}
