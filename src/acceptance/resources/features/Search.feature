Feature: Searching for datasets in the data catalog

  Scenario: Search by id
    Given the catalog application is alive
    When the search for id or name "1" is requested
    Then the app dataset is returned

  Scenario: Search by name
    Given the catalog application is alive
    When the search for id or name "app" is requested
    Then the app dataset is returned