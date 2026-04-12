import java.io.*;
import java.util.*;

public class StudentDAO {
    private static final String FILE_NAME = "Students.csv";
    
    // Read all students from the CSV file
    public List<Student> getAllStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean skipHeader = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip the header row
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                
                // Parse each line into student data
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    Student student = new Student(
                        parts[0], // studentId
                        parts[1], // name
                        parts[2], // branch
                        Integer.parseInt(parts[3]), // marks1
                        Integer.parseInt(parts[4]), // marks2
                        Integer.parseInt(parts[5]), // marks3
                        Integer.parseInt(parts[6]), // marks4
                        Integer.parseInt(parts[7]), // marks5
                        Double.parseDouble(parts[8])  // percentage
                    );
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading students from file: " + e.getMessage());
            throw e;
        }
        
        return students;
    }
    
    // Save all students back to the CSV file
    public void saveAllStudents(List<Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // First write the header
            writer.println("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage");
            
            // Then write each student's data
            for (Student student : students) {
                writer.println(student.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving students to file: " + e.getMessage());
            throw e;
        }
    }
    
    // Add a single student to the file
    public void addStudent(Student student) throws IOException {
        List<Student> students = getAllStudents();
        students.add(student);
        saveAllStudents(students);
    }
    
    // Update an existing student's information
    public boolean updateStudent(Student updatedStudent) throws IOException {
        List<Student> students = getAllStudents();
        
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(updatedStudent.getStudentId())) {
                students.set(i, updatedStudent);
                saveAllStudents(students);
                return true; // Student found and updated
            }
        }
        
        return false; // Student not found
    }
    
    // Remove a student from the file
    public boolean deleteStudent(String studentId) throws IOException {
        List<Student> students = getAllStudents();
        boolean studentRemoved = students.removeIf(student -> student.getStudentId().equals(studentId));
        
        if (studentRemoved) {
            saveAllStudents(students);
        }
        
        return studentRemoved;
    }
    
    // Create the initial CSV file with just the header
    public void createInitialFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage");
        } catch (IOException e) {
            System.err.println("Error creating initial file: " + e.getMessage());
            throw e;
        }
    }
}
