import java.util.Scanner;
public class EHSApplication {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        EHSApplication ehs = new EHSApplication();
        ehs.displayMessage();

    }
    private void mainMenu(){
        int input = Integer.parseInt(scanner.nextLine());
        Hospital hospital = new Hospital();
        while (true){
            switch (input){
                case 1: // admit a patient
                    Patient patient = new Patient();
                    patient.intialisePateint();
                    hospital.admitPatient(patient);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

    }

    private void displayMessage(){
        System.out.println("__          __       _                        _          ______ _    _  _____ \n" +
                " \\ \\        / /      | |                      | |        |  ____| |  | |/ ____|\n" +
                "  \\ \\  /\\  / /__  ___| | ___  _ __ ___   ___  | |_ ___   | |__  | |__| | (___  \n" +
                "   \\ \\/  \\/ / _ \\/ __| |/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  |  __| |  __  |\\___ \\ \n" +
                "    \\  /\\  /  __/ (__| | (_) | | | | | |  __/ | || (_) | | |____| |  | |____) |\n" +
                "     \\/  \\/ \\___|\\___|_|\\___/|_| |_| |_|\\___|  \\__\\___/  |______|_|  |_|_____/");
    }
}
