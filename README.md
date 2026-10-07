# ICT261 Student Services Desktop App

JavaFX student registration application for ICT261 Advanced Java.

## Features
- Register student: student number, full name, programme
- Required-field and 9-digit student-number validation
- Duplicate student-number prevention
- TableView using ObservableList
- Search by student number
- Delete selected student with confirmation
- H2 embedded database persistence
- Layered UI -> Controller -> Service -> Repository -> H2
- JUnit service tests

## Requirements
Java 21 LTS and Gradle 8+.

## Run
`gradle clean test run`

## Suggested commits
1. Create project skeleton with Gradle and package structure
2. Add student registration screen and controller
3. Add student service validation and tests
4. Add H2 persistence, search, delete and documentation
