# Feature: Delete regular activity
#   Description: An employee wishes to delete a regular activity
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

#   Scenario: 1. A employee deletes a regular activity
#     Given the user logs in using initials "mila"
#     When the user deletes regular activity 1
#     Then the regular activity with id 1 does not exist


# #ALTERNATIVE SCENARIOS
#   Scenario: 1a. Only owners of a regular activity can delete it
#     Given the user logs in using initials "brla"
#     When the user deletes regular activity 1
#     Then the error message "Du har ikke rettighed til at slette denne aktivitet" is given

#   Scenario: 1b. Guests cant delete a regular activity
#     When the user deletes regular activity 1
#     Then the error message "Login krævet" is given

