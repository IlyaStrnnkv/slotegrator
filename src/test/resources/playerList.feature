  Feature: Player list

    Scenario: Check open player list
      Given Login as admin
      When Open player list
      Then Check that player list is open

    Scenario: Check sort by status column
      Given Login as admin
      When Open player list
      Then Check sort by status column