Feature: the version can be retrieved
  Scenario: Get version from info endpoint
    When the client calls "/info"
    Then the client receives status code of 200
    And the client receives server version "1.0.0"