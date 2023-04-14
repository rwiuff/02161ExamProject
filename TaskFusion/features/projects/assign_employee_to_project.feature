Feature: Assign employees onto project
Description: Project leader assigns emplyees for a project
Actors: Project leader

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the year is 2023
    And the user logs in using initials "mila"
    And the user creates a project with title "Video game"
    And the user logs out

#MAIN SCENARIO

Scenario: 1. Employee assigns employee to project
    Given the user logs in using initials "mila"
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user assigns "brla" to the project "23001"
    Then the employee "brla" is assigned to the project "23001"
    And the employee "brla" have 1 projects

Scenario: 2. Project manager assigns an employee
    Given the user logs in using initials "mila"
    And the user takes the role as project leader on project "23001"
    And print exception
    And the user registers an employee with first name "Brian", last name "Laudrup"
    When the user assigns "brla" to the project "23001"
    And debug
    Then the employee "brla" is assigned to the project "23001"
    And the employee "brla" have 1 projects

#ALTERNATIVE SCENARIOS

Scenario: 1a. Employee assigns employee to project, with other employee as project leader
    Given the user logs in using initials "mila"
    And the user takes the role as project leader on project "23001"
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user registers an employee with first name "Pape", last name "Poulsen"
    And the user logs out
    When the user logs in using initials "brla"
    And the user assigns "papo" to the project "23001"
    Then the error message "Kun projektleder kan tildele medarbejdere til projektet" is given
    And the employee "papo" have 0 projects

Scenario: 1b. Employee doesn't exist
    Given the user logs in using initials "mila"
    And the user assigns "brla" to the project "23001"
    Then the error message "Ukendt medarbejder" is given