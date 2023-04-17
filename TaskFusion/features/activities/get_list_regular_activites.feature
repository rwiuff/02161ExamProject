Feature: Get a list of regular activities from an employee
Description: An employee can get a list of their regular activities.
Actors: Employees

Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "mila"

Scenario: 1. An employee can view a list of their regular activities
    When the user creates the regular activity "Ferie" with start week 2304 and end week 2306
    When the user creates the regular activity "Drik" with start week 2304 and end week 2305
    When the user creates the regular activity "Køb strippere" with start week 2304 and end week 2304
    When the user with initials "mila" requests a list of their regular activities
    Then the list of regular activities has size 3