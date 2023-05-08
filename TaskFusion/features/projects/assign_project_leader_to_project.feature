@AssignProjectLeaderToProject
Feature: Take on the role of project leader
Description: An employee can, when no project leader is assigned to a project, appoint themselves as project leader.
Actors: Employees

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user logs in using initials "mila"
    And the year is 2023
    And the user creates a project with title "Video game"
    And the user logs out
    
#MAIN SCENARIOS
Scenario: 1. An employee can appoint themselves as project leader
    Given the user logs in using initials "mila"
    When the user takes the role as project leader on project "23001"
    Then "mila" is the project leader on project "23001"

#ALTERNATIVE SCENARIOS
Scenario: 1a. An employee appoints himself as project leader on a project where there is already one.
    Given the user logs in using initials "brla"
    Given the user takes the role as project leader on project "23001"
    And the user logs in using initials "mila"
    Given the user takes the role as project leader on project "23001"
    Then the error message "Der kan kun være en projektleder" is given

Scenario: 1b. An employee appoints himself as project leader on a project that does not exist
    Given the user logs in using initials "mila"
    And the user takes the role as project leader on project "25001"
    Then the error message "Projektet kunne ikke findes i samlingen af projekter" is given