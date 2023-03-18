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

# Guest login: guest is logged in
# User login: the user logs in using initials "mila"
# Confirm login: the user with initials "mila" is logged in as an employee
# Logout: When the user logs out

# -------------------------------- #
# MEDARBEJDER GHERKINS
# -------------------------------- #

# Create: the user registers an employee with first first name "Michael", last name "Laudrup" and initials "mila"
# Exists: the application has a registered employee with first name "Michael", last name "Laudrup" and initials "mila"
# Confirm: an employee with first name "Michael", last name "Laudrup" and initials "mila" exists in the application
# Assign project leader: "mila" takes the role as project leader on project 23001
# Confirm project leader: "mila" is the project leader on project 23001
# Assign to project: "mila" assigns "brla" to the project titled "Video Game"
# Confirm assign: the employee "brla" is assigned to the project titled "Video Game"

# -------------------------------- #
# TIDS GHERKINS
# -------------------------------- #

# Get year: the year is 2023
# Setter: the user sets the start week to 2304
# Getter: the project has start week 2304

# -------------------------------- #
# PROJEKT GHERKINS
# -------------------------------- #

# Create: the user creates a project with title "Projektplanlægning"
# Exists: a project with title "Projektplanlægning" with project number 23001 exists in the application
# Confirm no project leader: project 23001 has no project leader assigned
# Set internal: the user sets the project as an internal project
# Confirm internal: the project is an internal project

# -------------------------------- #
# PROJEKT AKTIVITET GHERKINS
# -------------------------------- #

# Create: the user assigns the project activity "Graphics design" to project 23001
# Confirm: the project with the project number 23001 has a project activity titled "Graphics design"
# Exists: an activity with the title "Graphics design" exists within the project with project number 23001
# Set time bugdet: the user sets the time budget to 50 hours on the project activity with the title "Graphics design" and project number 23001
# Confirm time budget: the project activity with the title "Graphics design" and project number 23001 has a time budget of 50 hours
# Set start week: the user sets the start week to 2304 on the project activity with title "Graphics design" on the project with project number 23001
# Confirm start week: the project activity with the title "Graphics design" and project number 23001 has start week 2304
# Set end week: the user sets the end week to 2304 on the project activity with title "Graphics design" on the project with project number 23001
# Confirm end week: the project activity with title "Graphics design" and project number 23001 has end week 2304

# -------------------------------- #
# FAST AKTIVITET GHERKINS
# -------------------------------- #

# Create: the user creates the regular activity "Ferie" with start week 2304 and end week 2306
# Confirm: the user has a regular activity with title "Ferie" with start week 2304 and end week 2306

# -------------------------------- #
# ERROR GHERKINS
# -------------------------------- #

# Error message: the error message "Medarbejder med initialer mila ekisisterer allerede" is given

# -------------------------------- #
# ARBEJDSTID GHERKINS
# -------------------------------- #

# Create: the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number 23001
# Confirm: the user has 6 hours of registered work time on the project activity with title "Graphics design" and project number 23001
# Exists: the employee with initials "mila" has registered 6 hours of work time to the activity titled "Graphics design" in the project with project number 23001
# Getter, list: the list of work time registrations is requested for the activity titled "Graphics design" in the project with project number 23001
# Getter, timer: the total amount of registered work time is requested for the activity titled "Graphics design" in the project with project number 23001
# Setter, enkelt element: the user edits the first registered work time registration to 7 hours for the activity titled "Graphics design" in the project with project number 23001
# Setter, totale timer: the user edits the total amount of registered work time to 18 hours for the activity titled "Graphics design" in the project with project number 23001

# -------------------------------- #
# RAPPORT GHERKINS
# -------------------------------- #
