package Parking;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.Locale;

class ExitStation {
    private static long hours;
    private static long minutes;
    private final long costForHours = 5;

    public void calculateParkingDuration(int ticketId) {
        LocalDateTime entryTime = null;

        boolean fileFound = false;
        try {
            File inputFile = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Tickets\\\{ticketId}.txt");
            Scanner scanner = new Scanner(inputFile);
            boolean found = false;
            if (inputFile.exists()) {
                fileFound = true;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(": ", 2); // Limit the split to 2 parts
                if (data.length == 2 && data[0].equals("Date")) {
                    found = true;
                    String entryTimeString = data[1].trim();

                    // Custom date time formatter for the provided format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    entryTime = LocalDateTime.parse(entryTimeString, formatter);
                    break;
                }
            }

            scanner.close();

            if (!found) {
                System.out.println(STR."Transaction Date not found for Entry ID: \{ticketId}");
            }
        } catch (FileNotFoundException e) {
            System.out.println("ID not found.");
        }

        if (entryTime != null) {
            LocalDateTime exitTime = LocalDateTime.now(); // Example current exit time
            Duration duration = Duration.between(entryTime, exitTime);
            hours = duration.toHours();
            minutes = duration.toMinutesPart();
            boolean found = false;
        }
        if (fileFound && entryTime == null) {
            System.out.println("Entry time not found.");
        }
    }
    public long getHours(){
        return hours;
    }
    public long getMinutes(){
        return minutes;
    }

    public void writeShiftReport(String cost) {
        String filePath = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Reports\\shift_report.txt"; // Replace with your file path
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(STR."Date: \{formattedDate}, Cost: \{cost}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void deleteCustomer(int ID) {

            // Customer file in directory
            File file = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Tickets\\\{ID}.txt");

            // Check if the file (ID) exists and delete it because he is leaving in exit station.
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println(STR."Customer :\{ID} deleted successfully.");
                } else {
                    System.out.println(STR."Failed to delete the customer \{ID}");
                }
            } else {
                System.out.println(STR."File \{ID} does not exist.");
            }
        }
    public String getPayment(){
        long hourToPay = getHours();
        double minutesToPay = getMinutes();
        minutesToPay = minutesToPay/60;
        return String.format("%.2f", (hourToPay+minutesToPay)*costForHours);
    }
}



