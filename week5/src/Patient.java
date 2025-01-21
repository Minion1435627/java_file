import utils.Constants;

public class Patient {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int patientID;
    private String department;

    private Disease[] diseaseList;
    private Medicine[] medicineList;
    private Surgery surgery;
    private Billing billingDetails;


    private int healthPoints;




//    public Patient(String firstName, String lastName, String dateOfBirth, int patientID, String department) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.patientID = patientID;
//        this.department = department;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void intialisePateint(){
        Constants.keyboard.nextLine();
    }
}
