Feature: Get reamining activity progress
Description: Project leader gets remaining time on activity
Actors: Project manager

#MAIN SCENARIO
Scenario: Project leader gets remaining time on activity
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the year is 2025
    And the user logs in using initials "mila"
    And the user creates a project with title "Projektplanlægning"
    And the user takes the role as project leader on project "25001"
    And the user assigns the project activity "Graphics design" to project "25001" with startWeek "2501" and endWeek "2505"
    And the user sets the time budget to 32 hours on the project activity with the title "Graphics design" and project number "25001"
    And the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number "25001"
    When the user requests the remaining time on "Graphics design" on project "25001"
    Then the activity returns 26 hours