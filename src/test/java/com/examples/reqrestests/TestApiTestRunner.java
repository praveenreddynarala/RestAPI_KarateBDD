package com.examples.reqrestests;

import com.intuit.karate.KarateOptions;
import org.junit.jupiter.api.*;
import com.examples.GlobalApiRunner;

@KarateOptions(features = { "classpath:com/examples/reqrestests/testapi.feature" })
public class TestApiTestRunner extends GlobalApiRunner {

    @BeforeAll
    public static void beforeClass(){
        System.setProperty("karate.app", "users"); //Feature file or Test Suite name
    }

}
