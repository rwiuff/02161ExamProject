@CreateRegularActivity
Feature: Creating a regular activity
Description: An employee creates a regular activity in the application
Actors: employee
test

#BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the year is 2023

#MAIN SCENARIOS
Scenario: 1. Creating a regular activity
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2306"
    Then the user has a regular activity with title "Ferie" with start week "2304" and end week "2306"

#ALTERNATIVE SCENARIOS
Scenario: 1a. A guest is not able to create a regular activity
    Given the user logs out
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2306"
    Then the error message "Login krævet" is given

Scenario: 1b. A title is required to create a regular activity
    When the user creates the regular activity "" with start week "2304" and end week "2306"
    Then the error message "En titel mangler" is given

Scenario: 1c. A start week is required to create a regular activity
    When the user creates the regular activity "Ferie" with start week "" and end week "2306"
    Then the error message "En start uge mangler" is given

Scenario: 1d. An end week is required to create a regular activity
    When the user creates the regular activity "Ferie" with start week "2304" and end week ""
    Then the error message "En slut uge mangler" is given

Scenario: 1e. Start week needs to be before end week
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2303"
    Then the error message "Start uge skal være før eller ens med slut uge" is given

Scenario: 1f. Same start and end week is allowed
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2304"
    Then the user has a regular activity with title "Ferie" with start week "2304" and end week "2304"

Scenario: 1g. Start week can be greater than end week if start year is less than end year
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2402"
    Then the user has a regular activity with title "Ferie" with start week "2304" and end week "2402"

Scenario: 1h. Start week must be of 4 characters long
    When the user creates the regular activity "Ferie" with start week "23042" and end week "2305"
    Then the error message "Start uge og slut uge skal angives med fire cifre" is given

Scenario: 1i. End week must be of 4 characters long
    When the user creates the regular activity "Ferie" with start week "2304" and end week "2"
    Then the error message "Start uge og slut uge skal angives med fire cifre" is given

Scenario: 1j. The year of start week must be before the year of end week
    When the user creates the regular activity "Ferie" with start week "2404" and end week "2305"
    Then the error message "Start år skal være før eller ens med slut år" is given