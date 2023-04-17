Feature: Creating a project activity for a project without a project leader
  Description: An employee creates a project activity for a project without a project leader
  Actors: employee

#BACKGROUND
Background:
  Given the user registers an employee with first name "Michael", last name "Laudrup"
  And the user logs in using initials "mila"
  And the user creates a project with title "Video Game"

#MAIN SCENARIOS
Scenario: 1. Creating a project activity
  When the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  Then the project with the project number "23001" has a project activity titled "Graphics design"

Scenario: 2. A time budget can be added to a project activity
  Given the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  When the user sets the time budget to 50 hours on the project activity with the title "Graphics design" and project number "23001"
  Then the project activity with the title "Graphics design" and project number "23001" has a time budget of 50 hours

Scenario: 3. A start week can be set to a project activity
  Given the user assigns the project activity "Graphics design" to project "23001" with startWeek 2304 and endWeek 2305
  Then the project activity with the title "Graphics design" and project number "23001" has start week 2304

Scenario: 4. An end week can be set to a project activity
  Given the user assigns the project activity "Graphics design" to project "23001" with startWeek 2304 and endWeek 2305
  Then the project activity with title "Graphics design" and project number "23001" has end week 2305

#ALTERNATIVE SCENARIOS
Scenario: 1a. A guest is not able to create a project activity
  Given the user logs out
  When the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  Then the error message "Login krævet" is given

Scenario: 1b. A project activity title is unique in a project
  Given the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  When the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  Then the error message "Projekt aktivitet findes allerede" is given

Scenario: 2a. A guest is not able to set a time budget on a project activity
  Given the user logs out
  And the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  When the user sets the time budget to 50 hours on the project activity with the title "Graphics design" and project number "23001"
  Then the error message "Login krævet" is given

Scenario: 3a. A guest is not able to set a start week on a project activity
  Given the user logs out
  When the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  Then the error message "Login krævet" is given

Scenario: 3b. A start week needs to be before or the same as the end week
  Given the user logs in using initials "mila"
  And the user assigns the project activity "Graphics design" to project "23001" with startWeek 2 and endWeek 1
  Then the error message "Starttid skal være før eller ens med sluttid" is given

Scenario: 4a. A guest is not able to set an end week on a project activity
  Given the user logs out
  When the user assigns the project activity "Graphics design" to project "23001" with startWeek 1 and endWeek 2
  Then the error message "Login krævet" is given