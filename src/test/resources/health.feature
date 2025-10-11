Feature: Health check endpoint
  Scenario: Application health status is UP
    When the client calls "/actuator/health"
    Then the client receives status code of 404
