Feature: Creating a project activity for a project with assigned project leader
Description: A project leader creates a project activity for a project
Actors: employee, projectleader

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user registers an employee with first name "Mette", last name "Frederiksen"
    And the user logs in using initials "mefr"
    And the year is 2019
    And the user creates a project with title "Video game"
    And the user takes the role as project leader on project "19001"
    And the user logs out

#MAIN SCENARIOS
Scenario: 1. A project leader can create a project activity
    Given the user logs in using initials "mefr"
    When the user assigns the project activity "Graphics design" to project "19001" with startWeek "1901" and endWeek "1902"
    Then the project with the project number "19001" has a project activity titled "Graphics design"

#ALTERNATIVE SCENARIOS
Scenario: 1a. An employee is not able to create a project activity, when a projectleader is assigned
    Given the user logs in using initials "mila"
    When the user assigns the project activity "Graphics design" to project "19001" with startWeek "1901" and endWeek "1902"
    Then the error message "Kun projektlederen kan oprette en projekt aktivitet for dette projekt" is given