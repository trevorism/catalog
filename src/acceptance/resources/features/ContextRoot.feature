Feature: Context Root of List
  In order to use List, it must be available

  Scenario: ContextRoot on list
    Given the catalog application is alive
    When I navigate to "https://catalog.data.trevorism.com"
    Then then a link to the help page is displayed

  Scenario: Ping on list
    Given the catalog application is alive
    When I ping the application deployed to "https://catalog.data.trevorism.com"
    Then pong is returned, to indicate the service is alive

