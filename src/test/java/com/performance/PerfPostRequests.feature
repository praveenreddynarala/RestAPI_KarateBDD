Feature: Performance of POST Requests

  Background:
    * url baseURL

  @Post_Request
  Scenario: Post create a user and then get it by id
    Given path 'api/users'
    And request postRequestBody
    When method post
    Then status 201
    * def id = response.id
    * print 'created id is: ', id

  Scenario: update a user
    Given path 'api/users/2'
    And request putRequestBody
    When method put
    Then status 200