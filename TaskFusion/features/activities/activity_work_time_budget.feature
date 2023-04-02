# Feature: Set time budget for a regular activity
# Description: Project leader assigns the time bugdet for a given activity
# Actors: Project manager

# #MAIN SCENARIO
# Scenario: Project manager assigns a time budget
#     Given the application has a registered employee with first name "Michael", last name "Laudrup"
#     And a project with title "Projektplanlægning" with project number 23001 exists in the application
#     And "mila" takes the role as project leader on project 23001
#     And an activity with the title "Graphics design" exists within the project with project number 23001
#     And the user logs in using initials "mila"
#     And the user assigns a time budget of "32" hours to the activity titled "Graphics design"
#     Then the activity titled "Graphics design" has a time budget of "32" hours

# #ALTERNATIVE SCENARIOS
# Scenario: Employee assigns time buget
#     Given the application has a registered employee with first name "Michael", last name "Laudrup"
#     And a project with title "Projektplanlægning" with project number 23001 exists in the application
#     And an activity with the title "Graphics design" exists within the project with project number 23001
#     And the user logs in using initials "mila"
#     And the user assigns a time budget of "32" hours to the activity titled "Graphics design"
#     Then the activity titled "Graphics design" returns the error message "Only project managers can assign timebudgets"