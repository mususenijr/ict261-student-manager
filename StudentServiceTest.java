package zm.ac.mu.ict261.studentapp.service;

import org.junit.jupiter.api.*;
import zm.ac.mu.ict261.studentapp.model.Student;
import zm.ac.mu.ict261.studentapp.repository.StudentRepository;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    static class FakeRepo implements StudentRepository {
        final Map<String,Student> data = new HashMap<>();
        public void createTable() {}
        public void save(Student s) { data.put(s.getStudentNo(), s); }
        public Optional<Student> findByNo(String no) { return Optional.ofNullable(data.get(no)); }
        public List<Student> findAll() { return new ArrayList<>(data.values()); }
        public void deleteByNo(String no) { data.remove(no); }
    }
    private FakeRepo repo; private StudentService service;
    @BeforeEach void setUp() { repo = new FakeRepo(); service = new StudentService(repo); }

    @Test void registersValidStudent() {
        Student s = service.register("202509218","Mususeni Mumba","BSc Cybersecurity");
        assertEquals("202509218", s.getStudentNo());
        assertTrue(repo.data.containsKey("202509218"));
    }
    @Test void blankNameIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> service.register("202509218"," ","BCS"));
        assertTrue(repo.data.isEmpty());
    }
    @Test void invalidStudentNumberIsRejected() {
        assertThrows(IllegalArgumentException.class,
            () -> service.register("123","Mary Banda","BCS"));
    }
    @Test void duplicateNumberIsRejected() {
        service.register("202509218","Mususeni Mumba","BCS");
        assertThrows(IllegalStateException.class,
            () -> service.register("202509218","Copy Cat","BIT"));
        assertEquals(1, repo.data.size());
    }
}
