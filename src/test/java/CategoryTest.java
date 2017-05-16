import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class CategoryTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

	private Category testCategory;
	private Category secondCategory;
	@Before
	public void instanciate() {
		testCategory = new Category("MC2");
		secondCategory = new Category("MC3");
	}

  @Test
  public void category_instantiatesCorrectly_true() {
    assertEquals(true, testCategory instanceof Category);
  }
