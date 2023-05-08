![](/TaskFusionAlt.png)
# 02161 Exam Project [![Java CI with Maven](https://github.com/rwiuff/02161ExamProject/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/rwiuff/02161ExamProject/actions/workflows/maven.yml) [![Test Coverage](https://github.com/rwiuff/02161ExamProject/raw/gh-pages/badges/jacoco.svg?raw=true)](https://rwiuff.github.io/02161ExamProject/)
Exam Project for DTU course 02161 Software Engineering 1
## Running TaskFusion
### Compile and run
Open the root directory [02161ExamProject/TaskFusion](TaskFusion) in the terminal of your choice. There are two ways of running TaskFusion:
### Option 1: Directly in the terminal:
```
mvn compile exec:java
```
### Option 2: As an executable jar:
1. Compile the jar with the command:
```
mvn package
```
2. Run the jar using Java VM.
 - Windows:
```
java -jar '.\target\TaskFusion-1.0.0-jar-with-dependencies.jar'
```
 - Unix:
```
java -jar target/TaskFusion-1.0.0-jar-with-dependencies.jar
```
### Demo mode:
TaskFusion comes with a demo mode, that can load employees, projects and activities. To enable the demo mode, choose option 3 on the welcome screen.

If the demo mode is on, one can log in using the initials: **kasy**, **rawi**, **mach** or **mash**.

If the demo mode is off, one need to create an employee.

No passwords are required to run the application.
## Reports
Requirements and Design kan be found as [rapport_13_1.pdf](rapport_13_1.pdf).

Implementation and Test can be found as [rapport_13_2.pdf](rapport_13_2.pdf).
