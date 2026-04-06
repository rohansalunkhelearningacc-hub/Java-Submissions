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

// Full Time Employee Class
class FullTimeEmployee extends Employee {
    double baseSalary;
    double perfBonus;
    String role;

    FullTimeEmployee(String name, String PANNo, String joiningDate, String designation,
                     int empId, double baseSalary, double perfBonus, String role) {
        super(name, PANNo, joiningDate, designation, empId);
        this.baseSalary = baseSalary;
        this.perfBonus = perfBonus;
        this.role = role;
    }

    @Override
    double calcCTC() {
        if (role.equalsIgnoreCase("SWE")) {
            return baseSalary + perfBonus;
        } else if (role.equalsIgnoreCase("HR")) {
            double hiringCommission = 5000; // example
            return baseSalary + hiringCommission;
        }
        return baseSalary;
    }
}

// Contract Employee Class
class ContractEmployee extends Employee {
    int noOfHrs;
    double hourlyRate;

    ContractEmployee(String name, String PANNo, String joiningDate, String designation,
                     int empId, int noOfHrs, double hourlyRate) {
        super(name, PANNo, joiningDate, designation, empId);
        this.noOfHrs = noOfHrs;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double calcCTC() {
        return noOfHrs * hourlyRate;
    }
}

// Manager Class (Child of FullTimeEmployee)
class Manager extends FullTimeEmployee {
    double TA;
    double eduAllowance;

    Manager(String name, String PANNo, String joiningDate, String designation,
            int empId, double baseSalary, double perfBonus, String role,
            double TA, double eduAllowance) {

        super(name, PANNo, joiningDate, designation, empId, baseSalary, perfBonus, role);
        this.TA = TA;
        this.eduAllowance = eduAllowance;
    }

    @Override
    double calcCTC() {
        return baseSalary + perfBonus + TA + eduAllowance;
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        FullTimeEmployee emp1 = new FullTimeEmployee(
                "Rohan", "ABCDE1234F", "01-01-2023",
                "Developer", 101, 50000, 10000, "SWE");

        ContractEmployee emp2 = new ContractEmployee(
                "Amit", "PQRSX5678Z", "10-03-2024",
                "Consultant", 102, 120, 500);

        Manager mgr = new Manager(
                "Sneha", "LMNOP9876K", "15-06-2022",
                "Manager", 103, 80000, 20000, "Manager",
                10000, 15000);

        System.out.println("FullTimeEmployee CTC: " + emp1.calcCTC());
        System.out.println("ContractEmployee CTC: " + emp2.calcCTC());
        System.out.println("Manager CTC: " + mgr.calcCTC());
    }
}