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
