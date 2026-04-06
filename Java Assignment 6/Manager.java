import java.time.LocalDate;

class Manager extends FullTimeEmployee {
    private double TA;
    private double eduAllowance;

    public Manager(String name, String PANNo, LocalDate joiningDate, String designation, int empId,
                   double baseSalary, double perfBonus, double hiringCommission, String role,
                   double TA, double eduAllowance) {
        super(name, PANNo, joiningDate, designation, empId, baseSalary, perfBonus, hiringCommission, role);
        this.TA = TA;
        this.eduAllowance = eduAllowance;
    }

    @Override
    public double calcCTC() {
        return baseSalary + perfBonus + TA + eduAllowance;
    }

    // Getters
    public double getTA() { return TA; }
    public double getEduAllowance() { return eduAllowance; }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", PANNo='" + PANNo + '\'' +
                ", joiningDate=" + joiningDate +
                ", designation='" + designation + '\'' +
                ", empId=" + empId +
                ", baseSalary=" + baseSalary +
                ", perfBonus=" + perfBonus +
                ", hiringCommission=" + hiringCommission +
                ", role='" + role + '\'' +
                ", TA=" + TA +
                ", eduAllowance=" + eduAllowance +
                '}';
    }
}