Feature: Register work time on a project activity
Description: An employee registers their work time on a project activity.
Actors: employee

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the user creates a project with title "Video Game"
    And the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2

#MAIN SCENARIOS
Scenario: 1. Register work time on project activity
    When the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number "23001"
    Then the user has 6 hours of registered work time on the project activity with title "Graphics design" and project number "23001"

#ALTERNATIVE SCENARIOS
Scenario: 1.a Register worktime in half-hour increments
    When the user registers a work time of 6.5 hours to the project activity with title "Graphics design" in the project with project number "23001"
    Then the user has 6.5 hours of registered work time on the project activity with title "Graphics design" and project number "23001"

Scenario: 1.b Project not found
    When the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number "23002"
    Then the error message "Projektet kunne ikke findes i samlingen af projekter" is given 
    
Scenario: 1.c Project activity not found
    When the user registers a work time of 6 hours to the project activity with title "Regndans" in the project with project number "23001"
    Then the error message "Projektaktiviteten findes ikke." is given 