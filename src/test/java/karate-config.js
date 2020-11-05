function fn() {    
    var system = Java.type('java.lang.System');
    var env = karate.env; // get system property 'karate.env'
    var dir = karate.properties['karate.dir'];
    var app = karate.properties['karate.app'];

    karate.log('karate.env system property was:', env);
//    karate.log('karate.dir system property was:', dir);
//    karate.log('karate.app system property was:', app);

    //  karate.configure('ssl',true);
    karate.configure('logPrettyRequest',true);
    karate.configure('logPrettyResponse',true);

    if (!env) {
        env = 'dev';
    }
    var config = {
        env: env,
        myVarName: 'someValue'
    }
    if (env === 'dev') {
        config.URL='https://reqres.in'
    } else if (env == 'e2e') {
        // customize
    }

    karate.configure('connectTimeout',50000);
    karate.configure('readTimeout',50000);

    config.postRequestBody= dir+'/src/test/resources/fixtures/requests/post_request_sample.json';
    config.putRequestBody= dir+'/src/test/resources/fixtures/requests/put_request_sample.json';
    config.getRequestBody= dir+'/src/test/resources/fixtures/responses/sample.json';

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