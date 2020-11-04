package examples.users;

import com.intuit.karate.junit5.Karate;


//@KarateOptions(features = {"classpath:examples\\users\\users.feature"}/*,tags = {"@DBAOCreateOrg"}*/)
class UsersRunner {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("users").relativeTo(getClass());
    }

}
