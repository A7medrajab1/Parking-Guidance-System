package Parking;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("     ####################################################\n     #      Hello with our Parking Guidance System      #\n     ####################################################");

        try{
            while (true) {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        try{
                            customerModule(scanner);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        AdminModule(scanner);
                        break;
                    case 3:
                        OperatorModule(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }catch (Exception e){
            System.out.println("invalid");
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Customer Module");
        System.out.println("2. Admin Module");
        System.out.println("3. Operator Module");
        System.out.println("4. Exit");
    }

    private static void customerModule(Scanner scanner) {
        System.out.println("\n--- Customer Module ---");
        System.out.println("1. Entry to park");
        System.out.println("2. Exit from parking");
        System.out.println("3. Main Menu");
        System.out.print("Enter your choice: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter plate number: ");
                    String plateNumber = scanner.nextLine();
                    Customer customer = new Customer(plateNumber);
                    customer.printTicket();
                    System.out.println("\nPress enter to return to the Main Menu...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter entry ID for payment: ");
                    int entryIdForPayment = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Customer.payForParking(entryIdForPayment);

                    ExitStation exit = new ExitStation();
                    exit.calculateParkingDuration(entryIdForPayment);
                    exit.writeShiftReport(exit.getPayment());
                    exit.deleteCustomer(entryIdForPayment);

                    System.out.println("\nPress enter to return to the Main Menu...");
                    scanner.nextLine();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input.");
        }
    }


    public static void OperatorModule(Scanner scanner) {
        try {
                System.out.println("\n--- Operator Module ---");
                User userOperator = new User();

                System.out.print("Enter username: ");
                String name = scanner.next();

                System.out.print("Enter password: ");
                String pass = scanner.next();

                if (userOperator.login(name, pass) && userOperator.getUser().equals("operator")) {
            while (true) {
                    System.out.println("1. Entry Station");
                    System.out.println("2. Exit Station");
                    System.out.println("3. Log out");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            EntryStation entry = new EntryStation();
                            entry.getFreeSpot();
                            System.out.println("\nPress enter to show the Main Menu...");
                            scanner.nextLine();
                            break;
                        case 2:
                            System.out.print("Enter the ID number to calculate total parking hours: ");
                            int entryID = scanner.nextInt();
                            scanner.nextLine();
                            ExitStation exit = new ExitStation();
                            exit.calculateParkingDuration(entryID);
                            System.out.println(STR."Total parking hours for ID \{entryID} is: \{exit.getHours()} hours and \{exit.getMinutes()} minutes");
                            System.out.println("\nPress enter to show the Main Menu...");
                            scanner.nextLine();
                            break;
                        case 3:
                            return; // Log out
                        default:
                            System.out.println("Invalid choice.");
                    }
            }
                } else {
                    System.out.println("Invalid credentials or not an operator.");
                }
        } catch (Exception e) {
            System.out.println("Invalid input or error occurred.");
        }
    }


    private static void AdminModule(Scanner scanner) {
        System.out.println("\n--- Admin Module ---");
        Admin userAdmin = new Admin();
        System.out.print("Enter username :");
        String name = scanner.next();
        System.out.print("Enter password :");
        String pass = scanner.next();
        if(userAdmin.login(name,pass)&&(userAdmin.getUser()).equals("admin")){
            while(true){
            System.out.println("1. Add Spot");
            System.out.println("2. View total Spots");
            System.out.println("3. add / update / delete user");
            System.out.println("4. view shifts report with payment");
            System.out.println("5. view parked cars report");
            System.out.println("6. Log out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            EntryStation entry2 = new EntryStation();
            switch (choice) {
                case 1:
                    entry2.addSpot();
                    System.out.println(STR."spot added, ID id :\{entry2.getSpotID()}");
                    System.out.println("\nPress enter to show the Main Menu...");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println(STR."Total spots in parking is :\{entry2.getNumberOfSpots()}");
                    System.out.println("\nPress enter to show the Main Menu...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("1. Add user");
                    System.out.println("2. update user");
                    System.out.println("3. delete user");
                    System.out.print("Enter your choice: ");
                    int choice1 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    switch (choice1) {
                        case 1:
                            System.out.print("Enter username :");
                            String nameAdd = scanner.next();
                            System.out.print("Enter password :");
                            String passAdd = scanner.next();
                            System.out.print("Enter the role of user :");
                            String roleAdd = scanner.next();
                            userAdmin.addUser(nameAdd,passAdd,roleAdd);
                            break;
                        case 2:
                            System.out.print("Enter username :");
                            String nameUpd = scanner.next();
                            System.out.print("Enter the new password :");
                            String passNew = scanner.next();
                            userAdmin.updateUserPassword(nameUpd,passNew);
                            break;
                        case 3:
                            System.out.print("Enter username to delete:");
                            String nameDelete = scanner.next();
                            userAdmin.deleteUser(nameDelete);
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    System.out.println("\nPress enter to show the Main Menu...");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Shifts report :");
                    Admin.viewShiftReport();
                    System.out.println("\nPress enter to show the Main Menu...");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Parked car report :");
                    Admin.getAllParkedCarReports();
                    System.out.println("\nPress enter to show the Main Menu...");
                    scanner.nextLine();
                    break;
                case 6:
                    return; // Log out
                default:
                    System.out.println("Invalid choice.");
            }
            }
        }
        else{
            System.out.println(", not admin.");
            System.out.println("\nPress enter to show the Main Menu...");
            scanner.nextLine();
        }
    }
}