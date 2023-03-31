# Feature: Get reamining activity progress
# Description: Project leader gets remaining time on activity
# Actors: Project manager

# #MAIN SCENARIO
# Scenario: Project manager gets remaining time on activity
#     Given the application has a registered employee with first name "Michael", last name "Laudrup"
#     And a project with title "Video game" with project number 23001 exists in the application
#     And the user logs in using initials "mila"
#     And "mila" takes the role as project leader on project 23001
#     And an activity with the title "Graphics design" exists within the project with project number 23001
#     And the user assigns a time budget of 32 hours to the activity titled "Graphics design"
#     And the user registers a work time of 6 hours to the project activity with title "Graphics design" in the project with project number 23001
#     When the user gets the remaining time on "Graphics design"
#     Then the activity returns 26 hours