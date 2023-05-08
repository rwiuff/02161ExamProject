@RegisterEmployee
Feature: Register employee
Description: A new employee is added to the application
Actors: Guest

#MAIN SCENARIOS
Scenario: 1. Register an employee
    When the user registers an employee with first name "Michael", last name "Laudrup"
    Then an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application

#ALTERNATIVE SCENARIOS
Scenario: 1a. First name is required to register an employee
    When the user registers an employee with first name "", last name "Laudrup"
    Then the error message "Fornavn mangler" is given

Scenario: 1b. Last name is required to register an employee
    When the user registers an employee with first name "Michael", last name ""
    Then the error message "Efternavn mangler" is given

Scenario: 1c. If initials exists for a new employee, next letter in last name is used
    When the user registers an employee with first name "Michael", last name "Laudrup"
    And the user registers an employee with first name "Mikado", last name "Laudrup"
    Then an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application
    And an employee with first name "Mikado", last name "Laudrup" and initials "milu" exists in the application
    