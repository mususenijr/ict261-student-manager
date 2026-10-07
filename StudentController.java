package zm.ac.mu.ict261.studentapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import zm.ac.mu.ict261.studentapp.model.Student;
import zm.ac.mu.ict261.studentapp.repository.JdbcStudentRepository;
import zm.ac.mu.ict261.studentapp.repository.StudentRepository;
import zm.ac.mu.ict261.studentapp.service.StudentService;
import java.sql.Connection;
import java.sql.DriverManager;

public class StudentController {
    @FXML private TextField studentNoField, nameField, searchField;
    @FXML private ComboBox<String> programmeBox;
    @FXML private Label messageLabel, totalLabel;
    @FXML private Button saveButton, deleteButton;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student,String> studentNoColumn, nameColumn, programmeColumn;

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private StudentRepository repository;
    private StudentService service;

    @FXML private void initialize() {
        try {
            Connection c = DriverManager.getConnection("jdbc:h2:file:./data/studentdb");
            repository = new JdbcStudentRepository(c);
            repository.createTable();
            service = new StudentService(repository);

            programmeBox.setItems(FXCollections.observableArrayList(
                "BCS", "BIT", "BIS", "BSc Cybersecurity", "Other"));
            studentNoColumn.setCellValueFactory(d -> d.getValue().studentNoProperty());
            nameColumn.setCellValueFactory(d -> d.getValue().fullNameProperty());
            programmeColumn.setCellValueFactory(d -> d.getValue().programmeProperty());
            studentTable.setItems(students);
            deleteButton.setDisable(true);
            studentTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, old, now) -> deleteButton.setDisable(now == null));
            refreshAll();
        } catch (Exception e) { showError("Database error: " + e.getMessage()); }
    }

    @FXML private void handleSave() {
        try {
            Student s = service.register(studentNoField.getText(), nameField.getText(), programmeBox.getValue());
            students.add(s);
            showSuccess("Student registered successfully.");
            handleClear();
            updateTotal();
        } catch (IllegalArgumentException | IllegalStateException e) { showError(e.getMessage()); }
    }

    @FXML private void handleClear() {
        studentNoField.clear(); nameField.clear();
        programmeBox.getSelectionModel().clearSelection();
        studentNoField.requestFocus();
    }

    @FXML private void handleSearch() {
        String no = searchField.getText() == null ? "" : searchField.getText().trim();
        if (no.isBlank()) { refreshAll(); return; }
        repository.findByNo(no).ifPresentOrElse(
            s -> { students.setAll(s); showSuccess("Student found."); },
            () -> showError("No student found with number " + no + "."));
    }

    @FXML private void handleShowAll() { searchField.clear(); refreshAll(); }

    @FXML private void handleDelete() {
        Student s = studentTable.getSelectionModel().getSelectedItem();
        if (s == null) return;
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirm deletion");
        a.setHeaderText("Delete selected student?");
        a.setContentText(s.getStudentNo() + " - " + s.getFullName());
        a.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK) {
                repository.deleteByNo(s.getStudentNo());
                students.remove(s);
                updateTotal();
                showSuccess("Student deleted.");
            }
        });
    }

    private void refreshAll() { students.setAll(repository.findAll()); updateTotal(); }
    private void updateTotal() { totalLabel.setText("Total students: " + students.size()); }
    private void showSuccess(String m) { messageLabel.setText(m); messageLabel.setStyle("-fx-text-fill: green;"); }
    private void showError(String m) { messageLabel.setText(m == null ? "Unknown error." : m); messageLabel.setStyle("-fx-text-fill: red;"); }
}
