package examples.reqrestests;

import com.intuit.karate.KarateOptions;
import org.junit.jupiter.api.*;
import utils.GlobalApiRunner;

@KarateOptions(features = { "classpath:examples/reqrestests/testapi.feature" })
public class testApiTestRunner extends GlobalApiRunner {

    @BeforeAll
    public static void beforeClass(){
        System.setProperty("karate.app", "users"); //Feature file or Test Suite name
        System.setProperty("karate.env", "dev"); //Test Environment
        System.setProperty("karate.dir", System.getProperty("user.dir"));
    }

}
