import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/e_checkin", "cliff", "cliero");
    }
