function fn() {    
    var system = Java.type('java.lang.System');
    var appType = system.getProperty("karate.app")
    var env = karate.env;
    var app = karate.properties['karate.app'];

    karate.log('karate.env system property was ::: ', appType);

    var config = {
        ymlFile : karate.read('classpath:bdd-config.yaml')
    };

    if(!env) {
        env = config.ymlFile.testing.environment;
    }

    karate.log('Karate Testing Environment ::: ', env);

    if (env === 'DEV'){
        config.baseURL = config.ymlFile.dev.url; //Get url

        config.postRequestBody= config.ymlFile.dev.requests.post_request; //Get Post Request
        config.putRequestBody= config.ymlFile.dev.requests.put_request; //Get Put Request
        config.getRequestBody= config.ymlFile.dev.responses.sample_response; //Get Response
    } else if (env == 'QA'){
        config.baseURL = config.ymlFile.dev.url; //Get url
        config.postRequestBody= config.ymlFile.dev.requests.post_request; //Get Post Request
        config.putRequestBody= config.ymlFile.dev.requests.put_request; //Get Put Request
        config.getRequestBody= config.ymlFile.dev.responses.sample_response; //Get Response
    } else if (env == 'SIT'){
         config.baseURL = config.ymlFile.dev.url; //Get url
         config.postRequestBody= config.ymlFile.dev.requests.post_request; //Get Post Request
         config.putRequestBody= config.ymlFile.dev.requests.put_request; //Get Put Request
         config.getRequestBody= config.ymlFile.dev.responses.sample_response; //Get Response
     }

    karate.configure('connectTimeout',50000);
    karate.configure('readTimeout',50000);

    config.dyUuid = function() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
                return v.toString(16);
            });
        };

    config.scriptWait = function(seconds) {
        for(i = 0; i <= seconds; i++){
            java.lang.Thread.sleep(1*1000);
        }
    };

    return config;
}