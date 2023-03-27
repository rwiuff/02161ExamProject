Feature: Register employee
Description: A new employee is added to the application
Actors: Guest

#MAIN SCENARIOS
Scenario: 1. Register an employee
    When the user registers an employee with first name "Michael", last name "Laudrup"
    Then an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application

#ALTERNATIVE SCENARIOS
Scenario: 1a. Employee already exist
    And the application has a registered employee with first name "Michael", last name "Laudrup" 
    When the user registers an employee with first name "Michael", last name "Laudrup"
    Then the error message "Medarbejder ekisisterer allerede" is given

Scenario: 1b. First name is required to register an employee
    When the user registers an employee with first name "", last name "Laudrup"
    Then the error message "Fornavn mangler" is given

Scenario: 1c. Last name is required to register an employee
    When the user registers an employee with first name "Michael", last name ""
    Then the error message "Efternavn mangler" is given

## JEG SYNES VI SKAL DROPPE DER HER MED SAMME NAVN LOGIK - kasper
# Det bliver noget rod, da man så skal snappe ind i employee map'en, og den har man ikke direkte adgang til i Employee modellen
# Så skal vi i hvert fald gøre noget smartere med en form for singleton persistency lag, something-something. At passe map'en virker lidt skør måde måske

# Scenario: 1c. Employees with same name can be registered
#     And the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     When the user registers an employee with first name "Michael", last name "Laudrup" and initials "milb"
#     Then an employee with first name "Michael", last name "Laudrup" and initials "milb" exists in the application