# Layered Architecture

JavaFX/FXML -> StudentController -> StudentService -> StudentRepository -> JdbcStudentRepository -> H2

The controller handles UI events, the service contains validation/business rules, and the repository handles persistence.
