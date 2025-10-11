Feature: Actuator endpoints are available
  Scenario: Get application info
    When the client calls "/actuator/info"
    Then the client receives status code of 404