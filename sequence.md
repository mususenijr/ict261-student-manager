# UML Sequence: Register Student

User -> StudentController: click Register
StudentController -> StudentService: register(no, name, programme)
StudentService -> StudentRepository: findByNo(no)
StudentRepository --> StudentService: existing/not found
StudentService -> StudentRepository: save(student)
StudentRepository --> StudentService: saved
StudentService --> StudentController: Student
StudentController -> TableView: add row
StudentController -> User: success message

Error: invalid input causes IllegalArgumentException; duplicate number causes IllegalStateException; the controller displays the message and does not add the invalid student.
