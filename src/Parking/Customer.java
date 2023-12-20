package Parking;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

class Customer {
    private static int nextEntryId = 1; // Static variable to keep track of the next available entry ID
    private int entryId;
    private final String plateNumber ;
    private final Date transactionDate ;



    public Customer(String plateNumber) {
        entryId = getNextEntryId();
        this.plateNumber = plateNumber;
        transactionDate = new Date();
    }


    private static int getNextEntryId() {
        int currentEntryId = nextEntryId;  //to increment before return.
        nextEntryId++; // Increment for the next customer
        return currentEntryId;
    }

    public int getEntryId() {
        return entryId;
    }


    public void printTicket() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        File file = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Tickets\\\{entryId}.txt");
        File parkedCarsReportFile =new File("C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Reports\\parked_cars_report.txt");
        try{
        PrintWriter parkedCarsReportFileOutput = new PrintWriter(new BufferedWriter(new FileWriter(parkedCarsReportFile, true)));
            parkedCarsReportFileOutput.print(STR."ID: \{entryId}, ");
            parkedCarsReportFileOutput.print(STR."Plate Number: \{plateNumber}, ");
            parkedCarsReportFileOutput.println(STR."Date: \{formattedDate}.");
            parkedCarsReportFileOutput.close();
        }catch (Exception e){
                    System.out.println("\nInvalid");
        }
        while (file.exists()){
            entryId = getNextEntryId();
            file = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Tickets\\\{entryId}.txt");
        }
        try {
            PrintWriter output = new PrintWriter(file);
            output.println(STR."Entry ID: \{entryId}\n");
            output.println(STR."Plate Number: \{plateNumber}\n");
            output.println(STR."Date: \{formattedDate}\n");
            System.out.println(STR."Ticket printed successfully for Entry ID: \{entryId}");
            System.out.println(STR."Plate number: \{plateNumber}");
            System.out.println(STR."Transaction date: \{formattedDate}");
            Scanner scanner = new Scanner(System.in);
            output.close();
        } catch (IOException e) {
            System.out.println("An error occurred while printing the ticket.");
        }
    }

    public static void payForParking(int entryId) {
        ExitStation Exit = new ExitStation();
        Exit.calculateParkingDuration(entryId);
        System.out.println(STR."Payment for ID \{entryId} is \{Exit.getPayment()} $");
    }
}
