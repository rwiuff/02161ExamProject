Feature: Employees can login
Description: An employee logs in to the application
Actors: employee

#MAIN SCENARIOS
Scenario: 1. Login using initials
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    When the user logs in using initials "mila" 
    Then the user with initials "mila" is logged in as an employee

#ALTERNATIVE SCENARIOS
Scenario: 1a. Employee does not exist
    Given the user logs in using initials "mila" 
    Then the error message "Ukendt medarbejder" is given

Scenario: 1b. Login is case insensitive
    Given the application has a registered employee with first name "Michael", last name "Laudrup"
    When the user logs in using initials "MiLa" 
    Then the user with initials "mila" is logged in as an employee