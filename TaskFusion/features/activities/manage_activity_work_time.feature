Feature: View and edit registered work time on a project activity
  Description: An employee wishes to view or edit registered work time on an activity
  Actors: employee and guest

#BACKGROUND

Background:
  Given the user registers an employee with first name "Michael", last name "Laudrup"
  And the user registers an employee with first name "Brian", last name "Laudrup"
  And the user logs in using initials "mila"
  And the year is 2001
  And the user creates a project with title "Video Game"
  And the user assigns the project activity "Graphics design" to project "01001" with startWeek 1 and endWeek 2
  And the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number "01001" 
  #ID 1
  And the user registers a work time of 10 hours to the project activity with title "Graphics design" in the project with project number "01001"
  #ID 2
  And the user logs in using initials "brla"
  And the user registers a work time of 5 hours to the project activity with title "Graphics design" in the project with project number "01001"
  #ID 3
  And the user registers a work time of 8 hours to the project activity with title "Graphics design" in the project with project number "01001"
  #ID 4
  And the user logs in using initials "mila"

#MAIN SCENARIOS
Scenario: 1. An employee can view a list of work time registrations for an activity
  When the user requests a list of own worktime registrations for the activity titled "Graphics design" in the project with project number "01001"
  Then the worktime registration list contains 2 items

Scenario: 2. An employee can view the total amount of registered work time for an activity
    When the user requests a sum of own worktime registrations for the activity titled "Graphics design" in the project with project number "01001"
    Then 16 hours is returned

Scenario: 3. An employee can edit individual work time registrations for an activity
    And the user edits the worktime registration with id 1 to 7 hours
    When the user requests a sum of own worktime registrations for the activity titled "Graphics design" in the project with project number "01001"
    Then 17 hours is returned
    And worktime registration with id 1 has a worktime of 7 hours

# #ALTERNATIVE SCENARIOS
# Scenario: 1a. Guests are unable to view the list of work time registrations for an activity
#     Given none is logged in
#     When the list of work time registrations is requested for the activity titled "Graphics design" in the project with project number 01001
#     Then the error message "Login kræves" is given

# Scenario: 2a. Guests are unable to view the total amount of registered work time for an activity
#     Given none is logged in
#     When the total amount of registered work time is requested for the project activity titled "Graphics design" in the project with project number 01001
#     Then the error message "Login kræves" is given

# Scenario: 3a. Guests are unable to edit the total amount of registered work time for an activity
#     Given none is logged in
#     When the user edits the first registered work time registration to 7 hours for the activity titled "Graphics design" in the project with project number 01001
#     Then the error message "Login kræves" is given

# Scenario: 4a. Guests are unable to edit individual work time registrations for an activity
#     Given none is logged in
#     When the user edits the total amount of registered work time for the activity titled "Graphics design" in the project with project number 01001
#     Then the error message "Login kræves" is given

# Scenario: 1b. An employee wishes to view the list of work time registrations for an activity with no work time registered
#     And an activity with the title "Implement physics" exists within the project with project number 01001
#     When the list of work time registrations is requested for the activity titled "Implement physics" in the project with project number 01001
#     Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

# Scenario: 2b. An employee wishes to view the total amount of registered work time for an activity with no registered work time
#     And an activity with the title "Implement physics" exists within the project with project number 01001
#     When the the total amount of registered work time is requested for the activity titled "Implement physics" in the project with project number 01001
#     Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

# Scenario: 3b. An employee wishes to edit the total amount of registered work time for an activity with no registered work time
#     And an activity with the title "Implement physics" exists within the project with project number 01001
#     When the user edits the total amount of registered work time to 18 hours for the activity titled "Implement physics" in the project with project number 01001
#     Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu" is given

# Scenario: 4b. An employee wishes to edit individual work time registrations for an activity with no registered work time
#     And an activity with the title "Implement physics" exists within the project with project number 01001
#     When the user edits the first registered work time registration to 7 hours for the activity titled "Implement physics" in the project with project number 01001
#     Then the error message "Ingen arbejdstid er registreret for denne aktivitet endnu"