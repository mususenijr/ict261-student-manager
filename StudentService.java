package zm.ac.mu.ict261.studentapp.service;

import zm.ac.mu.ict261.studentapp.model.Student;
import zm.ac.mu.ict261.studentapp.repository.StudentRepository;

public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) { this.repository = repository; }

    public Student register(String no, String name, String programme) {
        validate(no, name, programme);
        no = no.trim();
        if (repository.findByNo(no).isPresent())
            throw new IllegalStateException("A student with number " + no + " already exists.");
        Student s = new Student(no, name.trim(), programme.trim());
        repository.save(s);
        return s;
    }

    private void validate(String no, String name, String programme) {
        if (isBlank(no) || isBlank(name) || isBlank(programme))
            throw new IllegalArgumentException("All fields are required.");
        if (!no.trim().matches("\\d{9}"))
            throw new IllegalArgumentException("Student number must contain exactly 9 digits.");
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }
}
