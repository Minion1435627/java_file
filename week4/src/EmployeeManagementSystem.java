import java.util.Scanner;
public class EmployeeManagementSystem {
    public static void main(String[] args) {

        // This is our driver program!
        // It just calls our classes so we can see they are working :)
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.printMenu();

        Scanner input = new Scanner(System.in);
        int choice = -1;
        boolean runMenuLoop = true;


        Employee emp = null;
        while (runMenuLoop){
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case Params.ADD_EMPLOYEE_CHOICE:
                    emp = ems.addEmployee(input);
                    System.out.println("Employee Record updated successfully!");
                    break;

                case Params.UPDATE_NAME_CHOICE:
                    System.out.println("Enter your name: ");
                    String name = input.nextLine();
                    emp.setName(name);
                    System.out.println("Employee Record updated successfully!");
                    break;

                case Params.UPDATE_DESIGNATION_CHOICE:
                    System.out.println("Enter your designation: ");
                    String designation= input.nextLine();
                    emp.setName(designation);
                    System.out.println("Employee Record updated successfully!");
                    break;

                case Params.UPDATE_NUM_HOURS_WORKED_CHOICE:
                    System.out.println("Enter your Hour Worked: ");
                    int numberOfHours = input.nextInt();
                    input.nextLine();
                    emp.setNumberOfHours(numberOfHours);
                    System.out.println("Employee Record updated successfully!");
                    break;
                case Params.CALC_SALARY_CHOICE:
                    System.out.println("The total Salary for this employee is: $" + emp.getTotalSalary());
                    break;
                case Params.EXIT_CHOICE:
                    System.out.println("Exiting Employee Management System. Thank you! ");
                    runMenuLoop = false;
                    break;

            }
            if (choice != Params.EXIT_CHOICE){
                System.out.println("Please select an option from the menu again.\n");
            }
        }




    }

    private void printMenu() {
        System.out.println("Welcome to the Employee Management System! Press\n" +
                " \n"+
                "1. To Add Employee\n" +
                "2. To Update Name\n" +
                "3. To Update Designation\n" +
                "4. To Update Number of Hours worked\n" +
                "5. To Calculate Salary\n" +
                "6. To Exit");
    }

    private Employee addEmployee(Scanner input){
        System.out.println("Enter your Employee ID: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your Designation: ");
        String designation = input.nextLine();
        System.out.println("Enter your Salary Per Hour: ");
        double salary = input.nextDouble();
        input.nextLine();
        int numberOfHours = 0;
        Employee emp1 = new Employee(name, id, designation, salary, numberOfHours);
        return emp1;


    }
}
