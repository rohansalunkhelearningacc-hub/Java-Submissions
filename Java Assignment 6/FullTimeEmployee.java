import java.time.LocalDate;

class FullTimeEmployee extends Employee {
    protected double baseSalary;
    protected double perfBonus;
    protected double hiringCommission;
    protected String role;

    public FullTimeEmployee(String name, String PANNo, LocalDate joiningDate, String designation, int empId,
                            double baseSalary, double perfBonus, double hiringCommission, String role) {
        super(name, PANNo, joiningDate, designation, empId);
        this.baseSalary = baseSalary;
        this.perfBonus = perfBonus;
        this.hiringCommission = hiringCommission;
        this.role = role;
    }

    @Override
    public double calcCTC() {
        if ("SWE".equals(role)) {
            return baseSalary + perfBonus;
        } else if ("HR".equals(role)) {
            return baseSalary + hiringCommission;
        } else {
            return baseSalary; // default
        }
    }

    // Getters
    public double getBaseSalary() { return baseSalary; }
    public double getPerfBonus() { return perfBonus; }
    public double getHiringCommission() { return hiringCommission; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "name='" + name + '\'' +
                ", PANNo='" + PANNo + '\'' +
                ", joiningDate=" + joiningDate +
                ", designation='" + designation + '\'' +
                ", empId=" + empId +
                ", baseSalary=" + baseSalary +
                ", perfBonus=" + perfBonus +
                ", hiringCommission=" + hiringCommission +
                ", role='" + role + '\'' +
                '}';
    }
}