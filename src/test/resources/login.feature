  Feature: Login

    Scenario: Correct login
      When Login as admin with correct data
      Then Check authorization is successful
