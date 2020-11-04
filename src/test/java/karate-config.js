function fn() {    
  var env = karate.env; // get system property 'karate.env'
  
  var userdir = karate.properties['userdir'];
  
  karate.log('karate.env system property was:', env);
  karate.configure('ssl',true);
  karate.configure('logPrettyRequest',true);
  karate.configure('logPrettyResponse',true);
  
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,
	myVarName: 'someValue'
  }
  if (env == 'dev') {
    config.URL='https://reqres.in'
  } else if (env == 'e2e') {
    // customize
  }
  karate.configure('connectTimeout',50000);
  karate.configure('readTimeout',50000);
  
  config.postRequestBody= userdir+'/Resources/RequestAndResponses/Requests/PostRequestSample.json';
  config.putRequestBody= userdir+'/Resources/RequestAndResponses/Requests/PutRequestSample.json';
  config.getRequestBody= userdir+'/Resources/RequestAndResponses/Responses/sample.json';
  
  var utils = {};
  utils.uuid = java.util.UUID.randomUUID();
  config.utils = karate.toMap(utils);
  
  return config;
}