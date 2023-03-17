Feature: Title
Description: One-liner
Actors: User of application

#BACKGROUND
Background:

#MAIN SCENARIOS
Scenario: 1. Title

#ALTERNATIVE SCENARIOS
Scenario: 2. Title

# -------------------------------- #
# BACKGROUND GHERKINS
# -------------------------------- #

Given guest is logged in
When the user logs in using initials "mila"
Then the user with initials "mila" is logged in as an employee
When the user logs out

# -------------------------------- #
# MEDARBEJDER GHERKINS
# -------------------------------- #

When the user registers an employee with first first name "Michael", last name "Laudrup" and initials "mila"
And the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
Then an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application
When the "mila" takes the role as project leader on project 23001
Then "mila" is the project leader on project 23001

# -------------------------------- #
# TIDS GHERKINS
# -------------------------------- #

And the year is 2023
When the user sets the start week to 2304
Then the project has start week 2304

# -------------------------------- #
# PROJEKT GHERKINS
# -------------------------------- #

When the user creates a project with title "Projektplanlægning"
And a project with title "Projektplanlægning" with project number 23001 exists in the application
And project 23001 has no project leader assigned
When the user sets the project as an internal project
Then the project is an internal project

# -------------------------------- #
# PROJEKT AKTIVITET GHERKINS
# -------------------------------- #

When the user assigns the project activity "Graphics design" to project 23001
Then the project with the project number 23001 has a project activity titled "Graphics design"
And an activity with the title "Graphics design" exists within the project with project number 23001
When the user sets the time budget to 50 hours on the project activity with the title "Graphics design" and project number 23001
Then the project activity with the title "Graphics design" and project number 23001 has a time budget of 50 hours
When the user sets the start week to 2304 on the project activity with title "Graphics design" on the project with project number 23001
Then the project activity with the title "Graphics design" and project number 23001 has start week 2304
When the user sets the end week to 2304 on the project activity with title "Graphics design" on the project with project number 23001
Then the project activity with title "Graphics design" and project number 23001 has end week 2304

# -------------------------------- #
# FAST AKTIVITET GHERKINS
# -------------------------------- #

When the user creates the regular activity "Ferie" with start week 2304 and end week 2306
Then the user has a regular activity with title "Ferie" with start week 2304 and end week 2306

# -------------------------------- #
# ERROR GHERKINS
# -------------------------------- #

Then the error message "Medarbejder med initialer mila ekisisterer allerede" is given