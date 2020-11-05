package examples.jsonplaceholder;

import com.intuit.karate.KarateOptions;
import org.junit.jupiter.api.BeforeAll;
import utils.GlobalApiRunner;

@KarateOptions(features = { "classpath:examples/jsonplaceholder/users.feature" })
public class usersTestRunner extends GlobalApiRunner {

    @BeforeAll
    public static void beforeClass(){
        System.setProperty("karate.app", "users"); //Feature file or Test Suite name
        System.setProperty("karate.env", "dev"); //TestEnvironment
        System.setProperty("karate.dir", System.getProperty("user.dir"));
    }
}
