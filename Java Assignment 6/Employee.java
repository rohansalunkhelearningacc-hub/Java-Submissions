import java.time.LocalDate;

abstract class Employee {
    protected String name;
    protected String PANNo;
    protected LocalDate joiningDate;
    protected String designation;
    protected int empId;

    public Employee(String name, String PANNo, LocalDate joiningDate, String designation, int empId) {
        this.name = name;
        this.PANNo = PANNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.empId = empId;
    }

    public abstract double calcCTC();

    // Getters
    public String getName() { return name; }
    public String getPANNo() { return PANNo; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public String getDesignation() { return designation; }
    public int getEmpId() { return empId; }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", PANNo='" + PANNo + '\'' +
                ", joiningDate=" + joiningDate +
                ", designation='" + designation + '\'' +
                ", empId=" + empId +
                '}';
    }
}