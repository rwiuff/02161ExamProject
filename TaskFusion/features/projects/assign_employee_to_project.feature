Feature: Assign employees onto project
Description: Project leader assigns emplyees for a project
Actors: Project leader

#BACKGROUND
Background:
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the user creates a project with title "Video game"
    And the user logs out

#MAIN SCENARIO
Scenario: Project manager assigns an employee
    Given the user logs in using initials "mila"
    And "mila" takes the role as project leader on project "23001"
    And the application has a registered employee with first name "Brian", last name "Laudrup"
    When the user assigns "brla" to the project "23001"
    Then the employee "brla" is assigned to the project "23001"

#ALTERNATIVE SCENARIOS

Scenario: Employee assigns employee to project
    Given the user logs in using initials "mila"
    And the application has a registered employee with first name "Brian", last name "Laudrup"
    And the user assigns "brla" to the project "23001"
    Then the employee "brla" is assigned to the project "23001"

Scenario: Employee assigns employee to project
    Given the user logs in using initials "mila"
    And "mila" takes the role as project leader on project "23001"
    And the application has a registered employee with first name "Brian", last name "Laudrup"
    And the application has a registered employee with first name "Pape", last name "Poulsen"
    And the user logs out
    When the user logs in using initials "brla"
    And the user assigns "papo" to the project "23001"
    Then the error message "Kun projektleder kan tildele medarbejdere til projektet" is given

# Scenario: Employee doesn't exist
#     Given user is logged in as "mila"
#     And "mila" takes the role as project leader on project 23001
#     And the user assigns "brla" to the project "23001"
#     Then the error message "Employee not found" is given