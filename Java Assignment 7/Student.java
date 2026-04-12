public class Student {
    private String studentId;
    private String name;
    private String branch;
    private int marks1, marks2, marks3, marks4, marks5;
    private double percentage;
    
    // Constructor to create a student with all details
    public Student(String studentId, String name, String branch, int marks1, int marks2, 
                   int marks3, int marks4, int marks5, double percentage) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.marks4 = marks4;
        this.marks5 = marks5;
        this.percentage = percentage;
    }
    
    // Getters - to access private fields
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public int getMarks1() { return marks1; }
    public int getMarks2() { return marks2; }
    public int getMarks3() { return marks3; }
    public int getMarks4() { return marks4; }
    public int getMarks5() { return marks5; }
    public double getPercentage() { return percentage; }
    
    // Setters - to modify private fields
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setMarks1(int marks1) { this.marks1 = marks1; }
    public void setMarks2(int marks2) { this.marks2 = marks2; }
    public void setMarks3(int marks3) { this.marks3 = marks3; }
    public void setMarks4(int marks4) { this.marks4 = marks4; }
    public void setMarks5(int marks5) { this.marks5 = marks5; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
    
    // Convert student data to CSV format string
    @Override
    public String toString() {
        return studentId + "," + name + "," + branch + "," + marks1 + "," + 
               marks2 + "," + marks3 + "," + marks4 + "," + marks5 + "," + percentage;
    }
}
