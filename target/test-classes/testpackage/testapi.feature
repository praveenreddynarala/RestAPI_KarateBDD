Feature: sample karate test script
  for help, see: https://github.com/intuit/karate/wiki/IDE-Support

  Background:
    * url URL
    * print utils
    * def postRequest = read(postRequestBody)
    
  @Get
  Scenario: get all users and then get the first user by id
    Given path 'api/users/2'
    When method get
    Then status 200

    * def first = response.data

    Given path 'api/users', first.id
    When method get
    Then status 200
    And match response == read(getRequestBody)

  @Post
  Scenario: create a user and then get it by id

    Given path 'api/users'
    And request postRequest
    When method post
    Then status 201

    * def id = response.id
    * print 'created id is: ', id
    
  @Put
  Scenario: update a user 

    Given path 'api/users/2'
    And request putRequestBody
    When method put
    Then status 200
    
  @Delete
  Scenario: delete a user

    Given path 'api/users/2'
    When method delete
    Then status 204
    
  @Patch
  Scenario: update a user 

    Given path 'api/users/2'
    And request putRequestBody
    When method patch
    Then status 200  
  