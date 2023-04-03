Feature: Creating a project
Description: An employee creates a project in the application
Actors: employee

#MAIN SCENARIOS
Scenario: 1. Creating a project
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the user logs in with initials "mila"
    And the year is 2019
    When the user creates a project with title "Projektplanlægning" 
    Then a project with title "Projektplanlægning" with project number "19001" exists in the application

Scenario: 2. A project can have a customer
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the user creates a project with title "Projektplanlægning"
    When the user sets customer "El-Giganten" on project "23001"
    Then the project "23001" has customer "El-Giganten"

# # Giver det mening? Skal vi bruge et "internt" flag senere?

# # Scenario: 3. A project can be an internal project
# #     Given the application has a registered employee with first name "Michael", last name "Laudrup"
# #     And the user logs in using initials "mila"
# #     And the user creates a project with title "Projektplanlægning" with project number 23001
# #     When the user sets the project as an internal project
# #     Then the project is an internal project

# Scenario: 4. A project can have a start week
#     Given the application has a registered employee with first name "Michael", last name "Laudrup"
#     And the user logs in using initials "mila"
#     And the user creates a project with title "Projektplanlægning" with project number 23001
#     When the user sets the start week to 2304 on "23001"
#     Then the project has start week 2304 on "23001"

Scenario: 5. Project numbers increments with each new project for the same year
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the year is 2023
    And the user logs in using initials "mila"
    And the user creates a project with title "Projektplanlægning"
    When the user creates a project with title "Half-life 3" 
    Then a project with title "Half-life 3" with project number "23002" exists in the application

#ALTERNATIVE SCENARIOS
Scenario: 1a. A guest is not able to create a project
    Given none is logged in
    And the year is 2023
    When the user creates a project with title "Projektplanlægning" 
    Then the error message "Kun medarbejdere kan oprette et projekt" is given

Scenario: 1b. A title is required to create a project
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    When the user creates a project with title ""
    Then the error message "En projekttitel mangler" is given

Scenario: 5a. Project number increments is reset with each year
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    And the year is 2021
    And the user logs in using initials "mila"
    And the user creates a project with title "Projektplanlægning"
    And the user creates a project with title "Programdesign"
    And the year is 2022
    And the user creates a project with title "Half-life 3" 
    And the user creates a project with title "Implementering" 

    Then a project with title "Projektplanlægning" with project number "21001" exists in the application
    And a project with title "Programdesign" with project number "21002" exists in the application
    And a project with title "Half-life 3" with project number "22001" exists in the application
    And a project with title "Implementering" with project number "22002" exists in the application

