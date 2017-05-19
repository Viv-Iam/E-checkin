import org.sql2o.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DB {

    private static URI dbUri;
    public static Sql2o sql2o;

    // public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-225-236-102.compute-1.amazonaws.com:5432/dbne9tqrap2vcb", "yknwznxkklyvbw", "c9b27f56b200d08b52193e15a4344f6e08c508f65d3d21a5fc3011a6663d3f16");

    static {

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/e_checkin");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
            }

            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e ) {
    }
  }
}
