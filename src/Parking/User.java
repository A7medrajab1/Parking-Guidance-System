package Parking;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class User {
    private final String USER_FILE_PATH = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Users\\user.txt";
    private static String user;

    public String getUserFilePath() {
        return USER_FILE_PATH;
    }


    public boolean login(String username, String password) {
        File adminFile = new File(USER_FILE_PATH);

        try (Scanner scanner = new Scanner(adminFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] adminData = line.split(",");

                if (adminData.length == 3) {
                    String storedUsername = adminData[0];
                    String storedPassword = adminData[1];
                    user = adminData[2];

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        // Match found, login successful
                        System.out.println("Login Successful!");
                        System.out.println(STR."Welcome, \{username} ! Your are an : \{user}\n");
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
        }

        // Login failed
        System.out.println("Login Failed! Incorrect username or password.");
        return false;
    }
    public  String getUser(){
        return user;
    }


// Helper method to read users from the file
    public List<String> readUsersFromFile() {
        List<String> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(USER_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                users.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
        }
        return users;
    }

// Helper method to write users to the file
    public void writeUsersToFile(List<String> users) {
        try (PrintWriter output = new PrintWriter(new FileWriter(USER_FILE_PATH))) {
            for (String user : users) {
                output.println(user);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing users to file.");
        }
    }

}
