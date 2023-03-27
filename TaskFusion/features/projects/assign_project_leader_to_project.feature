# Feature: Take on the role of project leader
# Description: An employee can, when no project leader is assigned to a project, appoint themselves as project leader.
# Actors: Employees

# #BACKGROUND
# Background:
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the application has a registered employee with first name "Brian", last name "Laudrup" and initials "brla"
#     And a project with title "Video game" with project number 23001 exists in the application

# #MAIN SCENARIOS
# Scenario: 1. An employee can appoint themselves as project leader
#     Given the user logs in using initials "mila"
#     When "mila" takes the role as project leader on project 23001
#     Then "mila" is the project leader on project 23001

# #ALTERNATIVE SCENARIOS
# Scenario: 1a. An employee appoints himself as project leader on a project where there is already one.
#     Given "brla" takes the role as project leader on project 23001
#     And the user logs in using initials "mila"
#     Given "mila" takes the role as project leader on project 23001
#     Then the error message "Der kan kun være en projektleder" is given

# Scenario: 1b. An employee appoints himself as project leader on a project that does not exist
#     Given the user logs in using initials "mila"
#     And "mila" takes the role as project leader on project 25001
#     Then the error message "Projektet kunne ikke findes i samlingen af projekter" is given