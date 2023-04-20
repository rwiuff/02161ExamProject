Feature: View regular activity
  Description: An employee wishes to view a regular activity
  Actors: employee

#MAIN SCENARIOS
  Background: 
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"
    And the user creates the regular activity "Ferie" with start week "2304" and end week "2306"
    And the user creates the regular activity "Syg" with start week "2304" and end week "2306"
    And the user logs out

  Scenario: 1. A employee can view a list of their regular activites
    Given the user logs in using initials "mila"
    When the user requests a list of own regular activities
    Then the regular activities list contains 2 items

  Scenario: 2. A employee can view a regular activity
    Given the user logs in using initials "mila"
    When the user requests a regular activity with id 2
    Then a regular activity is returned with id 2, title "Syg", start week "2304" and end week "2306"

#ALTERNATIVE SCENARIOS
  Scenario: 2a. Only owners of a regular activity can view it
    Given the user registers an employee with first name "Brian", last name "Laudrup"
    And the user logs in using initials "brla"
    When the user requests a regular activity with id 1
    Then the error message "Du har ikke rettighed til at se denne aktivitet" is given

  Scenario: 2b. Guests cant view a regular activity
    When the user requests a regular activity with id 1
    Then the error message "Login krævet" is given

  Scenario: 2c. A non existing id is given
    Given the user registers an employee with first name "Brian", last name "Laudrup"
    And the user logs in using initials "brla"
    When the user requests a regular activity with id 3
    Then the error message "Kunne ikke finde fast aktivitet" is given

  Scenario: 2d. A non existing parameter list is given
    Given the user registers an employee with first name "Mette Frederiksen", last name "Frederiksen"
    And the user logs in using initials "mefr"
    When the user requests a regular activity "Ferie" with start week "2404" and end week "2305"
    Then the user does not have such a regular activity
