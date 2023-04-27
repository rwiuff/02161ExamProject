Feature: Get a list of participating employees from a project
Description: An employee can get a list of employees assigned to a project.
Actors: Employees

Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the year is 2023
    And the user creates a project with title "Video game"
    And the user assigns the project activity "Graphics design" to project "23001" with startWeek "2304" and endWeek "2305"
    And the user logs out

Scenario: 1. An employee can view a list of employees assinged to an activity
    Given the user logs in using initials "mila"
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user assigns "brla" to the project "23001"
    And the user assigns "mefr" to the project "23001"
    When the user requests a list of employees assigned to the project with project number "23001"
    Then the employee list contains 2 items