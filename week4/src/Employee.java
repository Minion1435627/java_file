public class Employee {
    // TODO: Define the properties/ data attributes here
    private String name;
    private int employeeID;
    private String designation;
    private double salaryPerHour;
    private int numberOfHours;

    //TODO: Define constructors
    public Employee(String name, int employeeID, String designation, double salaryPerHour, int numberOfHours){
        this.name = name;
        this.employeeID = employeeID;
        this.designation = designation;
        this.salaryPerHour = salaryPerHour;
        this.numberOfHours = numberOfHours;
    }

    public Employee(Employee employee){
        this.name = employee.getName();
        this.employeeID = employee.getEmployeeID();
        this.designation = employee.getDesignation();
        this.salaryPerHour = employee.getSalaryPerHour();
        this.numberOfHours = employee.getNumberOfHours();

    }
    public Employee(){

    }

    public double getTotalSalary(){
        return salaryPerHour*numberOfHours;
    }



    //TODO: Define getters/setters

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public void setNumberOfHours(int numberOfHour) {
        this.numberOfHours = numberOfHour;
    }
}
