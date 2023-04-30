# Feature: View regular activity
#   Description: An employee wishes to view a regular activity
#   Actors: employee

# #MAIN SCENARIOS
#   Background: 
#     Given the user registers an employee with first name "Michael", last name "Laudrup"
#     Given the user registers an employee with first name "Brian", last name "Laudrup"
#     And the user logs in using initials "mila"
#     #ID1
#     And the user creates the regular activity "Ferie" with start week 2304 and end week 2306
#     #ID2 
#     And the user creates the regular activity "Syg" with start week 2304 and end week 2306
#     And the user logs out

#   Scenario: 1. A employee change the title of a regular activity
#     Given the user logs in using initials "mila"
#     When the user sets title "Konference" on regular activity 1
#     Then the regular activity with id 1 have title "Konference"

#   Scenario: 1. A employee change the start week of a regular activity
#     Given the user logs in using initials "mila"
#     When the user sets start week 2301 on regular activity 1
#     Then the regular activity with id 1 have start week 2301

#   Scenario: 1. A employee change the end week of a regular activity
#     Given the user logs in using initials "mila"
#     When the user sets end week 2308 on regular activity 1
#     Then the regular activity with id 1 have end week 2308

# #ALTERNATIVE SCENARIOS
#   Scenario: 1a. Only owners of a regular activity can set the title
#     Given the user logs in using initials "brla"
#     When the user sets title "Konference" on regular activity 1
#     Then the error message "Du har ikke rettighed til at rette denne aktivitet" is given

#   Scenario: 1b. Guests cant set the title of a regular activity
#     When the user sets title "Konference" on regular activity 1
#     Then the error message "Login krævet" is given

#   Scenario: 2a. Only owners of a regular activity can set the start week
#     Given the user logs in using initials "brla"
#     When the user sets start week 2301 on regular activity 1
#     Then the error message "Du har ikke rettighed til at rette denne aktivitet" is given

#   Scenario: 2b. Guests cant set the start week of a regular activity
#     When the user sets start week 2301 on regular activity 1
#     Then the error message "Login krævet" is given

#   Scenario: 3a. Only owners of a regular activity can set the end week
#     Given the user logs in using initials "brla"
#     When the user sets end week 2301 on regular activity 1
#     Then the error message "Du har ikke rettighed til at rette denne aktivitet" is given

#   Scenario: 3b. Guests cant set the end week of a regular activity
#     When the user sets end week 2301 on regular activity 1
#     Then the error message "Login krævet" is given

