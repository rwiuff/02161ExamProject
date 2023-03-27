Feature: Employees can log out
Description: An employee logs out of the application
Actors: employee

#MAIN SCENARIOS
Scenario: 1. Logout
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    When the user logs out
    Then none is logged in