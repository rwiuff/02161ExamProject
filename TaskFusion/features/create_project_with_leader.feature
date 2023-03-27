Feature: Creating a project activity for a project with assigned project leader
Description: A project leader creates a project activity for a project
Actors: employee, projectleader

#BACKGROUND
Background:
    Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
    And the application has a registered employee with first name "Mette", last name "Frederiksen" and initials "mefr"
    And a project with title "Video game" with project number 23001 exists in the application
    And "mefr" takes the role as project leader on project 23001

#MAIN SCENARIOS
Scenario: 1. A project leader can create a project activity
    Given the user logs in using initials "mefr"
    When the user assigns the project activity "Graphics design" to project 23001
    Then the project with the project number 23001 has a project activity titled "Graphics design"

#ALTERNATIVE SCENARIOS
Scenario: 1a. An employee is not able to create a project activity
    Given the user logs in using initials "mefr"
    When the user assigns the project activity "Graphics design" to project 23001
    Then the error message "Kun projektlederen kan redigere denne projekt aktivitet" is given