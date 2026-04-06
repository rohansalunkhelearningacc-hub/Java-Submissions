import java.time.LocalDate;

public class EmployeeDemo {
    public static void main(String[] args) {
        // Create employees
        FullTimeEmployee swe = new FullTimeEmployee("Alice Johnson", "PAN123456", LocalDate.of(2020, 5, 15),
                "Software Engineer", 101, 50000.0, 10000.0, 0.0, "SWE");
        FullTimeEmployee hr = new FullTimeEmployee("Bob Smith", "PAN789012", LocalDate.of(2019, 3, 10),
                "HR Manager", 102, 45000.0, 0.0, 5000.0, "HR");
        ContractEmployee contractor = new ContractEmployee("Charlie Brown", "PAN345678", LocalDate.of(2021, 7, 20),
                "Contract Developer", 103, 160, 50.0);
        Manager manager = new Manager("Diana Prince", "PAN901234", LocalDate.of(2018, 1, 5),
                "Project Manager", 104, 60000.0, 15000.0, 0.0, "Manager", 5000.0, 3000.0);

        // Print table header
        System.out.println("Employee Details");
        System.out.println("=================================================================================");
        System.out.printf("%-20s %-15s %-12s %-20s %-6s %-10s%n", "Name", "PAN No", "Join Date", "Designation", "EmpId", "CTC");
        System.out.println("=================================================================================");

        // Print each employee
        printEmployee(swe);
        printEmployee(hr);
        printEmployee(contractor);
        printEmployee(manager);
    }

    private static void printEmployee(Employee emp) {
        System.out.printf("%-20s %-15s %-12s %-20s %-6d %-10.2f%n",
                emp.getName(),
                emp.getPANNo(),
                emp.getJoiningDate().toString(),
                emp.getDesignation(),
                emp.getEmpId(),
                emp.calcCTC());
    }
}