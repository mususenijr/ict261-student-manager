package zm.ac.mu.ict261.studentapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty studentNo = new SimpleStringProperty();
    private final StringProperty fullName = new SimpleStringProperty();
    private final StringProperty programme = new SimpleStringProperty();

    public Student(String studentNo, String fullName, String programme) {
        setStudentNo(studentNo);
        setFullName(fullName);
        setProgramme(programme);
    }
    public String getStudentNo() { return studentNo.get(); }
    public void setStudentNo(String v) { studentNo.set(v); }
    public StringProperty studentNoProperty() { return studentNo; }
    public String getFullName() { return fullName.get(); }
    public void setFullName(String v) { fullName.set(v); }
    public StringProperty fullNameProperty() { return fullName; }
    public String getProgramme() { return programme.get(); }
    public void setProgramme(String v) { programme.set(v); }
    public StringProperty programmeProperty() { return programme; }
    @Override public String toString() {
        return getStudentNo() + " - " + getFullName() + " (" + getProgramme() + ")";
    }
}
