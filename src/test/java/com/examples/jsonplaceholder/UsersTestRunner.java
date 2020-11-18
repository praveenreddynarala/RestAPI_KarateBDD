package com.examples.jsonplaceholder;

import com.intuit.karate.KarateOptions;
import org.junit.jupiter.api.BeforeAll;
import com.examples.GlobalApiRunner;

@KarateOptions(features = { "classpath:com/examples/jsonplaceholder/users.feature" })
public class UsersTestRunner extends GlobalApiRunner {

    @BeforeAll
    public static void beforeClass(){
        System.setProperty("karate.app", "users"); //Feature file or Test Suite name
    }
}
