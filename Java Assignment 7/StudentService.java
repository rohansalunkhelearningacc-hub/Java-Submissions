import java.io.IOException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;
    
    // Set up the data access object
    public StudentService() {
        this.studentDAO = new StudentDAO();
    }
    
    // Start with some basic student data
    public void initializeWithSampleData() throws IOException {
        studentDAO.createInitialFile();
        
        // Add our first two students
        Student alice = new Student("S001", "Alice", "Computer Science", 85, 90, 78, 0, 0, 0.0);
        Student bob = new Student("S002", "Bob", "Mechanical Engineering", 76, 82, 88, 0, 0, 0.0);
        
        studentDAO.addStudent(alice);
        studentDAO.addStudent(bob);
    }
    
    // Add three new students with zero marks to start with
    public void addNewStudents() throws IOException {
        Student charlie = new Student("S003", "Charlie", "Electrical Engineering", 88, 75, 92, 0, 0, 0.0);
        Student diana = new Student("S004", "Diana", "Civil Engineering", 92, 85, 79, 0, 0, 0.0);
        Student eve = new Student("S005", "Eve", "Chemical Engineering", 78, 88, 85, 0, 0, 0.0);
        
        studentDAO.addStudent(charlie);
        studentDAO.addStudent(diana);
        studentDAO.addStudent(eve);
    }
    
    // Now fill in the real marks for everyone
    public void updateAllStudentsWithCorrectMarks() throws IOException {
        List<Student> students = studentDAO.getAllStudents();
        
        for (Student student : students) {
            // Give each student their actual marks
            switch (student.getStudentId()) {
                case "S001": // Alice's real scores
                    student.setMarks1(85);
                    student.setMarks2(90);
                    student.setMarks3(78);
                    student.setMarks4(88);
                    student.setMarks5(92);
                    break;
                    
                case "S002": // Bob's real scores
                    student.setMarks1(76);
                    student.setMarks2(82);
                    student.setMarks3(88);
                    student.setMarks4(85);
                    student.setMarks5(79);
                    break;
                    
                case "S003": // Charlie's real scores
                    student.setMarks1(88);
                    student.setMarks2(75);
                    student.setMarks3(92);
                    student.setMarks4(80);
                    student.setMarks5(85);
                    break;
                    
                case "S004": // Diana's real scores
                    student.setMarks1(92);
                    student.setMarks2(85);
                    student.setMarks3(79);
                    student.setMarks4(88);
                    student.setMarks5(90);
                    break;
                    
                case "S005": // Eve's real scores
                    student.setMarks1(78);
                    student.setMarks2(88);
                    student.setMarks3(85);
                    student.setMarks4(82);
                    student.setMarks5(87);
                    break;
            }
        }
        
        studentDAO.saveAllStudents(students);
    }
    
    // Calculate what each student scored overall
    public void calculateAndUpdatePercentages() throws IOException {
        List<Student> students = studentDAO.getAllStudents();
        
        for (Student student : students) {
            // Add up all five subject marks
            double total = student.getMarks1() + student.getMarks2() + student.getMarks3() +
                          student.getMarks4() + student.getMarks5();
            
            // Calculate average percentage
            double percentage = total / 5.0;
            student.setPercentage(percentage);
        }
        
        studentDAO.saveAllStudents(students);
    }
    
    // Remove a student who left the school
    public boolean deleteStudent(String studentId) throws IOException {
        return studentDAO.deleteStudent(studentId);
    }
    
    // Get list of all current students
    public List<Student> getAllStudents() throws IOException {
        return studentDAO.getAllStudents();
    }
}
