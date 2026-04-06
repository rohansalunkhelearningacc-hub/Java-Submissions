// Abstract Root Class
abstract class Employee {
    String name;
    String PANNo;
    String joiningDate;
    String designation;
    int empId;

    // Constructor
    Employee(String name, String PANNo, String joiningDate, String designation, int empId) {
        this.name = name;
        this.PANNo = PANNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    // Abstract Method
    abstract double calcCTC();
}
