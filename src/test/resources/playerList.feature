  Feature: Player list

    Scenario: Check open player list
      Given Login as admin
      When Open player list
      Then Check that player list is open

    Scenario:
      Given Login as admin
      And Open list of Players
      When sortByStatusColumn
      Then checkSortByStatusColumn