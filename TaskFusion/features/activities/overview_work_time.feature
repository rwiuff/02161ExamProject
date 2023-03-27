# Feature: View summary of registered work time pr. activity
# Description: A project leader can view a summary of registered work time per activity
# Actors: Project leader and employee

# #BACKGROUND
# Background:
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the application has a registered employee with first name "Lars", last name "Svendsen" and initials "lasv"
#     And a project with title "Video game" with project number 23001 exists in the application
#     And "lasv" takes the role as project leader on project 23001
#     And multiple activities with registered work time for "mila" exists within the project titled "Video game"

# #MAIN SCENARIO
# Scenario: 1. The project leader can view a summary of registered work time pr. activity
#     Given the user logs in using initials "lasv"
#     And "lasv" is the project leader on project 23001
#     When "lasv" wishes to view a summary of the work time for the project titled "Video game"
#     Then a summary of registered work time pr. activity under the project titled "Video game" is shown

# #ALTERNATIVE SCENARIO
# Scenario: 1a. An employee recieves an error message when attempting to view the summary of registered work time
#     When "mila" wishes to view a summary of the work time for the project titled "Video game"
#     Then the error message "Kun projektlederen kan tilgå oversigten af arbejdstid for projektet" is given

# Scenario: 1b. A project leader can only view the summary of registered work time for the project to which the person is assigned
#     Given a project with title "Web development" with project number 23002 exists in the application
#     When "lasv" wishes to view a summary of the worktime for the project titled "Web development"
#     Then an error message "Kun projektlederen kan tilgå oversigten af arbejdstid for projektet" is given

# Scenario: 1c. The project leader recieves an error message if no work time has been registered
#     When "lasv" wishes to view a summary of the worktime for the project "Video game"
#     Then the error message "Ingen arbejdstid er registreret under dette projekt endnu" is given