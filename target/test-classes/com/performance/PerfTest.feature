Feature: Performance

  Background:
    * url URL
#    * def postRequest = read(postRequestBody)

  @Get_Request
  Scenario: Get all users and validate status code
    Given path 'api/users/9'
    When method get
    Then status 200

  @Get_Request
  Scenario: Get specific user and validate its response
    Given path 'api/users/2'
    When method get
    Then status 200
    And match response == read(getRequestBody)

  @Get_Request
  Scenario: Get all users
    Given path '/api/users?page=2'
    When method get
    Then status 200

  @Post_Request
  Scenario: Get create a user and then get it by id
    Given path 'api/users'
    And request read(postRequestBody)
    When method post
    Then status 201
    * def id = response.id
    * print 'created id is: ', id

#  @Put
#  Scenario: update a user
#    Given path 'api/users/2'
#    And request putRequestBody
#    When method put
#    Then status 200
#
#  @Delete
#  Scenario: delete a user
#    Given path 'api/users/2'
#    When method delete
#    Then status 204