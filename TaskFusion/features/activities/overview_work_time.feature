Feature: View summary of registered work time pr. activity
Description: A project leader can view a summary of registered work time per activity
Actors: Project leader and employee

# #BACKGROUND
Background:
    Given the user registers an employee with first name "Michael", last name "Laudrup"
    And the user registers an employee with first name "Lars", last name "Svendsen"
    And the year is 2025
    And the user logs in using initials "lasv"
    And the user creates a project with title "Video game"
    And the user takes the role as project leader on project "25001"
    And the user assigns the project activity "Graphics design" to project "25001" with startWeek 1 and endWeek 5
    And the user assigns the project activity "Gameplay" to project "25001" with startWeek 1 and endWeek 2
    And the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number "25001" 
    #ID 1
    And the user registers a work time of 10 hours to the project activity with title "Graphics design" in the project with project number "25001"
    #ID 2
    And the user logs in using initials "mila"
    And the user registers a work time of 10 hours to the project activity with title "Gameplay" in the project with project number "25001"
    #ID 1
    And the user registers a work time of 10 hours to the project activity with title "Gameplay" in the project with project number "25001"
    #ID 2

#MAIN SCENARIO
Scenario: 1. The project leader can view a summary of registered work time pr. activity
    Given the user logs in using initials "lasv"
    When the user requests a list of all worktime registrations for the activity titled "Gameplay" in the project with project number "25001"
    Then the worktime registration list contains 2 items

#ALTERNATIVE SCENARIO
# Scenario: 1a. An employee recieves an error message when attempting to view the summary of registered work time
    # When "mila" wishes to view a summary of the work time for the project titled "Video game"
    # Then the error message "Kun projektlederen kan tilgå oversigten af arbejdstid for projektet" is given

# Scenario: 1b. A project leader can only view the summary of registered work time for the project to which the person is assigned
#     Given a project with title "Web development" with project number 23002 exists in the application
#     When "lasv" wishes to view a summary of the worktime for the project titled "Web development"
#     Then an error message "Kun projektlederen kan tilgå oversigten af arbejdstid for projektet" is given

# Scenario: 1c. The project leader recieves an error message if no work time has been registered
#     When "lasv" wishes to view a summary of the worktime for the project "Video game"
#     Then the error message "Ingen arbejdstid er registreret under dette projekt endnu" is given