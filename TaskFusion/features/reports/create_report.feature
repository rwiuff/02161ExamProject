Feature: Create project report
Description: User creates a report
Actors: User

#BACKGROUND
Background: 
    Given the date is 20.04.2025
    And the user registers an employee with first name "Michael", last name "Laudrup"
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user logs in using initials "mila"
    And the user creates a project with title "TaskFusion"
    And the user takes the role as project leader on project "25001"
    And the user assigns the project activity "AcceptanceTests" to project "25001" with startWeek "2506" and endWeek "2507"
    And the user sets the time budget to 47 hours on the project activity with the title "AcceptanceTests" and project number "25001"
    And the user assigns the project activity "UnitTests" to project "25001" with startWeek "2507" and endWeek "2508"
    And the user sets the time budget to 80 hours on the project activity with the title "UnitTests" and project number "25001"
    And the user registers a work time of 6.5 hours to the project activity with title "AcceptanceTests" in the project with project number "25001"
    And the user registers a work time of 1 hours to the project activity with title "AcceptanceTests" in the project with project number "25001"
    And the user registers a work time of 3.5 hours to the project activity with title "UnitTests" in the project with project number "25001"
    And the user registers a work time of 2.5 hours to the project activity with title "UnitTests" in the project with project number "25001"
    And the user logs in using initials "brla"
    And the user registers a work time of 2 hours to the project activity with title "AcceptanceTests" in the project with project number "25001"
    And the user registers a work time of 3 hours to the project activity with title "AcceptanceTests" in the project with project number "25001"
    And the user registers a work time of 5 hours to the project activity with title "UnitTests" in the project with project number "25001"
    And the user registers a work time of 7 hours to the project activity with title "UnitTests" in the project with project number "25001"
    And the user logs out

Scenario: 1a. User generates report
    Given the user logs in using initials "mila"
    When the user generates a report for project "25001"
    Then the number of reports for project "25001" is 1

Scenario: 1b. User generates multiple reports
    Given the user logs in using initials "mila"
    And the date is 20.04.2025
    And the user generates a report for project "25001"
    And the date is 21.04.2025
    And the user generates a report for project "25001"
    Then the number of reports for project "25001" is 2

Scenario: 2a. Login is required to generate a report
    Given the user generates a report for project "25001"
    Then the error message "Login krævet" is given

Scenario: 2b. Project leader is required to generate a report
    Given the user logs in using initials "mila"
    And the user creates a project with title "FaskTusion"
    When the user generates a report for project "25002"
    Then the error message "Projektet mangler en projektleder for at genererer rapporter" is given

Scenario: 2c. Only project leader can generate a report
    Given the user logs in using initials "brla"
    When the user generates a report for project "25001"
    Then the error message "Kun projektlederen kan generere rapporter" is given