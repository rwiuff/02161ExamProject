Feature: Register employee
Description: A new employee is added to the application
Actors: Guest

#MAIN SCENARIOS
Scenario: 1. Register an employee
    When the user registers an employee with first first name "Michael", last name "Laudrup"
    Then an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application

#ALTERNATIVE SCENARIOS
Scenario: 1a. Employee already exist
    And the application has a registered employee with first name "Michael", last name "Laudrup" 
    When the user registers an employee with first first name "Michael", last name "Laudrup" and initials "mila"
    Then the error message "Medarbejder med initialer mila ekisisterer allerede" is given

# Scenario: 1b. First and last name is required to register an employee
#     When the user registers an employee with first name "", last name ""
#     Then the error message "For- og/eller efternavn mangler" is given

# Scenario: 1c. Initials is required to register an employee
#     When the user registers the employee an employee with first name "Michael", last name "Laudrup" and initials ""
#     Then the error message "Initialer mangler" is given

# Scenario: 1d. Employees with same name can be registered
#     And the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     When the user registers an employee with first name "Michael", last name "Laudrup" and initials "milb"
#     Then an employee with first name "Michael", last name "Laudrup" and initials "milb" exists in the application