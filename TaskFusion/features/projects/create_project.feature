@CreateProject
Feature: Creating a project
Description: An employee creates a project in the application
Actors: employee

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"

#MAIN SCENARIOS
Scenario: 1. Creating a project
    Given the year is 2019
    When the user creates a project with title "Projektplanlægning" 
    Then a project with title "Projektplanlægning" with project number "19001" exists in the application
    And the employee "mila" have 1 projects

Scenario: 2. A project can have a customer
    Given the year is 2023
    And the user creates a project with title "Projektplanlægning"
    When the user sets customer "El-Giganten" on project "23001"
    Then the project "23001" has customer "El-Giganten"
    And the employee "mila" have 1 projects

Scenario: 3. A project in an internal project, if it does not have a customer
    Given the year is 2023
    And the user creates a project with title "Projektplanlægning"
    Then the project "23001" is an internal project
    And the employee "mila" have 1 projects

Scenario: 4. Project numbers increments with each new project for the same year
    Given the year is 2023
    And the user creates a project with title "Projektplanlægning"
    When the user creates a project with title "Half-life 3" 
    Then a project with title "Half-life 3" with project number "23002" exists in the application
    And the employee "mila" have 2 projects

#ALTERNATIVE SCENARIOS
Scenario: 1a. A guest is not able to create a project
    Given the user logs out
    And the year is 2023
    When the user creates a project with title "Projektplanlægning" 
    Then the error message "Login krævet" is given

Scenario: 1b. A title is required to create a project
    When the user creates a project with title ""
    Then the error message "En projekttitel mangler" is given
    And the employee "mila" have 0 projects

Scenario: 3a. A project in an external project, if it has a customer
    Given the year is 2023
    And the user creates a project with title "Projektplanlægning"
    When the user sets customer "El-Giganten" on project "23001"
    Then the project "23001" is an external project
    And the employee "mila" have 1 projects

Scenario: 5a. Project number increments is reset with each year
    Given the year is 2021
    And the user creates a project with title "Projektplanlægning"
    And the user creates a project with title "Programdesign"
    And the year is 2022
    And the user creates a project with title "Half-life 3" 
    And the user creates a project with title "Implementering" 

    Then a project with title "Projektplanlægning" with project number "21001" exists in the application
    And a project with title "Programdesign" with project number "21002" exists in the application
    And a project with title "Half-life 3" with project number "22001" exists in the application
    And a project with title "Implementering" with project number "22002" exists in the application
    And there is 4 projects in the application
    And the employee "mila" have 4 projects

