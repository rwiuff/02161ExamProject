Feature: Manage project(s)
Description: An employee can manage projects
Actors: employee

#BACKGROUND
Background:
    Given the year is 2019
    And the user registers an employee with first name "Brian", last name "Laudrup"
    And the user registers an employee with first name "Michael", last name "Laudrup"
    And the user logs in using initials "brla"
    And the user creates a project with title "Half-Life 3" 
    And the user creates a project with title "Cure Cancer" 
    And the user logs in using initials "mila"
    And the user creates a project with title "Projektplanlægning" 

#MAIN SCENARIOS
Scenario: 1. Get a list of user projects
    Then the user have 1 projects
