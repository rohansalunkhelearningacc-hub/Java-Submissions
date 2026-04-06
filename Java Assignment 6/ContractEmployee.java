import java.time.LocalDate;

class ContractEmployee extends Employee {
    private int noOfHrs;
    private double hourlyRate;

    public ContractEmployee(String name, String PANNo, LocalDate joiningDate, String designation, int empId,
                            int noOfHrs, double hourlyRate) {
        super(name, PANNo, joiningDate, designation, empId);
        this.noOfHrs = noOfHrs;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcCTC() {
        return noOfHrs * hourlyRate;
    }

    // Getters
    public int getNoOfHrs() { return noOfHrs; }
    public double getHourlyRate() { return hourlyRate; }

    @Override
    public String toString() {
        return "ContractEmployee{" +
                "name='" + name + '\'' +
                ", PANNo='" + PANNo + '\'' +
                ", joiningDate=" + joiningDate +
                ", designation='" + designation + '\'' +
                ", empId=" + empId +
                ", noOfHrs=" + noOfHrs +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}