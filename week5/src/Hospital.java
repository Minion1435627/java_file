public class Hospital {
    private Patient[] patientList;

    public Hospital() {
        this.patientList = new Patient[10];
    }

    public Patient[] getPatientList() {
        return patientList;
    }

    public void setPatientList(Patient[] patientList) {
        this.patientList = patientList;
    }
    public void admitPatient(Patient patient){
        for (int i = 0; i < patientList.length; i++ ){
            if (patientList[i] == null){
                patientList[i] = patient;
                break;
            }
        }
    }
}
