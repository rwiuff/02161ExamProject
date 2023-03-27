# Feature: Creating a project
# Description: An employee creates a project in the application
# Actors: employee

# #MAIN SCENARIOS
# Scenario: 1. Creating a project
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the user with initials "mila" is logged in as an employee
#     And the year is 2023
#     When the user creates a project with title "Projektplanlægning" 
#     Then a project with title "Projektplanlægning" with project number 23001 exists in the application

# Scenario: 2. A project can have a customer
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the user logs in using initials "mila"
#     And a project with title "Projektplanlægning" with project number 23001 exists in the application
#     When the user sets customer "El-Giganten" on project 23001
#     Then the project 23001 has customer "El-Giganten"

# Scenario: 3. A project can be an internal project
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the user logs in using initials "mila"
#     And a project with title "Projektplanlægning" with project number 23001 exists in the application
#     When the user sets the project as an internal project
#     Then the project is an internal project

# Scenario: 4. A project can have a start week
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the user logs in using initials "mila"
#     And a project with title "Projektplanlægning" with project number 23001 exists in the application
#     When the user sets the start week to 2304
#     Then the project has start week 2304

# #ALTERNATIVE SCENARIOS
# Scenario: 1a. A guest is not able to create a project
#     Given none is logged in
#     And the year is 2023
#     When the user creates a project with title "Projektplanlægning" 
#     Then the error message "Kun medarbejdere kan oprette et projekt" is given

# Scenario: 1b. A title is required to create a project
#     Given the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
#     And the user logs in using initials "mila"
#     When the user creates a project with title ""
#     Then the error message "En projekttitel mangler" is given