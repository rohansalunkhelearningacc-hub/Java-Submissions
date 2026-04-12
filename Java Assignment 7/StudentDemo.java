import java.io.IOException;
import java.util.List;

public class StudentDemo {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        
        try {
            System.out.println("=== STUDENT GRADE MANAGEMENT SYSTEM ===\n");
            
            // Step 1: Create our starting file with some students
            System.out.println("Step 1: Creating initial student file");
            studentService.initializeWithSampleData();
            showCurrentFile();
            
            // Step 2: Add three more students (they start with zeros)
            System.out.println("Step 2: Adding three new students with zero marks");
            studentService.addNewStudents();
            showCurrentFile();
            
            // Step 3: Fill in everyone's real grades
            System.out.println("Step 3: Updating all students with correct marks");
            studentService.updateAllStudentsWithCorrectMarks();
            showCurrentFile();
            
            // Step 4: Calculate final percentages for report cards
            System.out.println("Step 4: Calculating and adding percentages");
            studentService.calculateAndUpdatePercentages();
            showCurrentFile();
            
            // Step 5: One student transferred schools, so remove them
            System.out.println("Step 5: Removing student S003 (transferred out)");
            boolean deleted = studentService.deleteStudent("S003");
            if (deleted) {
                System.out.println("Student S003 removed successfully!");
            } else {
                System.out.println("Could not find student S003");
            }
            showCurrentFile();
            
            // Show a nice summary of remaining students
            System.out.println("Final Student Summary:");
            showStudentSummary(studentService.getAllStudents());
            
            // Show how we handle problems
            System.out.println("Testing error handling:");
            testErrorHandling();
            
        } catch (IOException e) {
            System.err.println("Oops! Something went wrong with file operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Quick way to see what's in our file right now
    private static void showCurrentFile() {
        System.out.println("\nCurrent file contents:");
        try {
            java.nio.file.Files.lines(java.nio.file.Paths.get("Students.csv"))
                 .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Couldn't read file to show contents: " + e.getMessage());
        }
    }
    
    // Show a clean summary table of students
    private static void showStudentSummary(List<Student> students) {
        System.out.printf("%-8s %-15s %-25s %-12s%n", "ID", "Name", "Branch", "Percentage");
        System.out.println("--------------------------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-8s %-15s %-25s %-12.2f%%%n", 
                student.getStudentId(), 
                student.getName(), 
                student.getBranch(), 
                student.getPercentage());
        }
        System.out.println();
    }
    
    // Test what happens when things go wrong
    private static void testErrorHandling() {
        try {
            // This file doesn't exist, so it should fail
            java.nio.file.Files.readAllLines(java.nio.file.Paths.get("MissingFile.csv"));
        } catch (IOException e) {
            System.err.println("Caught expected error: " + e.getMessage());
        }
    }
}
