Feature: Set time budget for a regular activity
Description: Project leader assigns the time bugdet for a given activity
Actors: Project manager

#BACKGROUND
Background:
  Given the user registers an employee with first name "Michael", last name "Laudrup"
  And the user registers an employee with first name "Mette", last name "Frederiksen"
  And the year is 2015
  And the user logs in using initials "mila"
  And the user creates a project with title "Projektplanlægning"
  And "mila" takes the role as project leader on project "15001"
  And the user assigns the project activity "Graphics design" to project "15001" with startWeek 1 and endWeek 5
  And the user logs out

#MAIN SCENARIO
Scenario: 1. Project manager assigns a time budget
    Given the user logs in using initials "mila"
    And the user sets the time budget to 32 hours on the project activity with the title "Graphics design" and project number "15001"
    Then the project activity with the title "Graphics design" and project number "15001" has a time budget of 32 hours

#ALTERNATIVE SCENARIOS
Scenario: 1a. Employee assigns time buget
    Given the user logs in using initials "mefr"
    And the user sets the time budget to 32 hours on the project activity with the title "Graphics design" and project number "15001"
    Then the error message "Kun projektlederen kan tildele tidsbudgetter" is given