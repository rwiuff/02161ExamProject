Feature: Register work time on a project activity
Description: An employee registers their work time on a project activity.
Actors: employee

#BACKGROUND
Background:
    Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
    And a project with title "Video game" with project number 23001 exists in the application
    And an activity with the title "Graphics design" exists within the project with project number 23001
    And the user logs in using initials "mila"

#MAIN SCENARIOS
Scenario: 1. Register work time on project activity
    When the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number 23001
    Then the user has 6 hours of registered work time on the project activity with title "Graphics design" and project number 23001

#ALTERNATIVE SCENARIOS
Scenario: 1.a Register worktime in half-hour increments
    When the user registers a work time of 6.5 hours to the project activity with title "Graphics design" in the project with project number 23001
    Then the user has 6.5 hours of registered work time on the project activity with title "Graphics design" in the project with project number 23001

Scenario: 1.b Project not found
    When the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number 23002
    Then the error message "Ukendt projekt" is given 
    
Scenario: 1.c Project activity not found
    When the user registers a work time of 6 hours to the project activity with title "Regndans" in the project with project number 23001
    Then the error message "Ukendt projekt aktivitet" is given 