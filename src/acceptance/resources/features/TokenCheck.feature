Feature: Valid clientId and clientSecret
  Catalog uses a clientId and clientSecret pair to obtain a token.

  Scenario: Token can be fetched
    Given the catalog application is alive
    When the endpoint tester internal endpoint is invoked
    Then a response is returned successfully