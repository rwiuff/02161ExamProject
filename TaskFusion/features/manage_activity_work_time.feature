Feature: View and edit registered work time on a project activity
    Description: An employee wishes to view or edit registered work time on an activity
    Actors: employee and guest

#BACKGROUND
Background:
    Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
    And a project with title "Vide game" with project number 23001 exists in the application
    And an activity with the title "Graphics design" exists within the project with project number 23001
    And the employee with initials "mila" has registered 6 hours of work time to the activity titled "Graphics design" in the project with project number 23001
    And the employee with initials "mila" has registered 10 hours of work time to the activity titled "Graphics design" in the project with project number 23001

#MAIN SCENARIOS
Scenario: 1. An employee can view a list of work time registrations for an activity
    Given the user logs in using initials "mila"
    When the list of work time registrations is requested for the activity titled "Graphics design" in the project with project number 23001
    Then a list containing 2 items with the values 6 and 10 is returned

Scenario: 2. An employee can view the total amount of registered work time for an activity
    Given the user logs in using initials "mila"
    When the total amount of registered work time is requested for the activity titled "Graphics design" in the project with project number 23001
    Then 16 hours is returned

Scenario: 3. An employee can edit the total amount of registered work time for an activity
    Given the user logs in using initials "mila"
    And the user edits the total amount of registered work time to 18 hours for the activity titled "Graphics design" in the project with project number 23001
    When the total amount of registered work time is requested for the activity titled "Graphics design" in the project with project number 23001
    Then 18 hours is returned

Scenario: 4. An employee can edit individual work time registrations for an activity
    Given the user logs in using initials "mila"
    And the user edits the first registered work time registration to 7 hours for the activity titled "Graphics design" in the project with project number 23001
    When the list of work time registrations is requested for the activity titled "Graphics design" in the project with project number 23001
    Then a list containing 2 items with the values 7 and 10 hours is returned

#ALTERNATIVE SCENARIOS
Scenario: 1a. Guests are unable to view the list of work time registrations for an activity
    Given none is logged in
    When the list of work time registrations is requested for the activity titled "Graphics design" in the project with project number 23001
    Then the error message "Login kræves" is given

Scenario: 2a. Guests are unable to view the total amount of registered work time for an activity
    Given none is logged in
    When the total amount of registered work time is requested for the project activity titled "Graphics design" in the project with project number 23001
    Then the error message "Login kræves" is given

Scenario: 3a. Guests are unable to edit the total amount of registered work time for an activity
    Given none is logged in
    When the user edits the first registered work time registration to 7 hours for the activity titled "Graphics design" in the project with project number 23001
    Then the error message "Login kræves" is given

Scenario: 4a. Guests are unable to edit individual work time registrations for an activity
    Given none is logged in
    When the user edits the total amount of registered work time for the activity titled "Graphics design" in the project with project number 23001
    Then the error message "Login kræves" is given

Scenario: 1b. An employee wishes to view the list of work time registrations for an activity with no work time registered
    Given the user logs in using initials "mila"
    And an activity with the title "Implement physics" exists within the project with project number 23001
    When the list of work time registrations is requested for the activity titled "Implement physics" in the project with project number 23001
    Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

Scenario: 2b. An employee wishes to view the total amount of registered work time for an activity with no registered work time
    Given the user logs in using initials "mila"
    And an activity with the title "Implement physics" exists within the project with project number 23001
    When the the total amount of registered work time is requested for the activity titled "Implement physics" in the project with project number 23001
    Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

Scenario: 3b. An employee wishes to edit the total amount of registered work time for an activity with no registered work time
    Given the user logs in using initials "mila"
    And an activity with the title "Implement physics" exists within the project with project number 23001
    When the user edits the total amount of registered work time to 18 hours for the activity titled "Implement physics" in the project with project number 23001
    Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

Scenario: 4b. An employee wishes to edit individual work time registrations for an activity with no registered work time
    Given the user logs in using initials "mila"
    And an activity with the title "Implement physics" exists within the project with project number 23001
    When the user edits the first registered work time registration to 7 hours for the activity titled "Implement physics" in the project with project number 23001
    Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu"