package zm.ac.mu.ict261.studentapp.repository;

import zm.ac.mu.ict261.studentapp.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void createTable();
    void save(Student student);
    Optional<Student> findByNo(String studentNo);
    List<Student> findAll();
    void deleteByNo(String studentNo);
}
