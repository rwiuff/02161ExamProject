# Feature: Assign employees onto project
# Description: Project leader assigns emplyees for a project
# Actors: Project leader

# #BACKGROUND
# Background:
#     Given the application has a registered employee with first name "Michael", last name "Laudrup"
#     And a project with title "Video game" with project number 23001 exists in the application

# #MAIN SCENARIO
# Scenario: Project manager assigns an employee
#     Given the user logs in using initials "mila"
#     And "mila" takes the role as project leader on project 23001
#     And the application has a registered employee with first name "Brian", last name "Laudrup" and initials "brla"
#     And "mila" assigns "brla" to the project titled "Video Game"
#     Then the employee "brla" is assigned to the project titled "Video Game"

# #ALTERNATIVE SCENARIOS
# Scenario: Employee assigns employee to project
#     Given the user is logged in as "mila"
#     And there is an employee in the application with first name "Brian", last name "Laudrup" and initials "brla"
#     And "mila" assigns "brla" to the project titled "Video Game"
#     Then the project titled "Video Game" returns the error message "Only project managers can assign employees"

# Scenario: Employee doesn't exist
#     Given user is logged in as "mila"
#     And "mila" takes the role as project leader on project 23001
#     And "mila" assigns "brla" to the project titled "Video Game"
#     Then the project titled "Video Game" returns the error message "Employee not found"