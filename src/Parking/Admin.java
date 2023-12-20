package Parking;

import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Admin extends User {

//    private static final String USER_FILE_PATH = User.getUserFilePath();


    public void addUser(String username, String password, String role) {
        try (PrintWriter output = new PrintWriter(new FileWriter(getUserFilePath(), true))) {
            output.println(STR."\{username},\{password},\{role}");
            System.out.println("User added successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while adding the user.");
        }
    }

    // Method to update an existing user's password
    public void updateUserPassword(String username, String newPassword) {
        List<String> users = readUsersFromFile();
        List<String> updatedUsers = new ArrayList<>();

        for (String user : users) {
            String[] userData = user.split(",");
            if (userData[0].equals(username)) {
                userData[1] = newPassword;
            }
            updatedUsers.add(String.join(",", userData));
        }

        writeUsersToFile(updatedUsers);
        System.out.println("User password updated successfully!");
    }

    // Method to delete an existing user
    public void deleteUser(String username) {
        List<String> users = readUsersFromFile();
        List<String> updatedUsers = new ArrayList<>();

        for (String user : users) {
            String[] userData = user.split(",");
            if (!userData[0].equals(username)) {
                updatedUsers.add(user);
            }
        }

        writeUsersToFile(updatedUsers);
        System.out.println("User deleted successfully!");
    }

    @Override
    public List<String> readUsersFromFile() {
        List<String> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(getUserFilePath()))) {
            while (scanner.hasNextLine()) {
                users.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admin file not found.");
        }
        return users;
    }

    public static void viewShiftReport() {
        // Logic to fetch and display shift report
        String filePath = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Reports\\shift_report.txt"; // Replace with your file path
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Date:") && line.contains(", Cost:")) {
                    String date = line.substring(6, line.indexOf(", Cost:")).trim();
                    String cost = line.substring(line.indexOf("Cost:") + 6).trim();
                    System.out.println(STR."Date :\{date} and cost :\{cost}");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getAllParkedCarReports() {
        List<String> records = new ArrayList<>();
        String filePath = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Reports\\parked_cars_report.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                    records.add(line);
                    records.add("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(records);

    }
}


