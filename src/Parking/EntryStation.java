package Parking;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EntryStation {
    private static int nextSpotID = 1;
    private int SpotId;
    private int numberOfSpots ;
    private int numberOfFreeSpots = 0;

    private boolean isOccupied;


    public EntryStation(){
        this.SpotId = getNextSpotId();
        this.isOccupied = false;
    }

    //overloading
    public EntryStation(int id) {
        this.SpotId = id;
    }
    public int getNextSpotId (){
        int currentSpotId = nextSpotID;  //to increment before return.
        nextSpotID++; // Increment for the next spot
        return currentSpotId;
    }


    public int getSpotID() {
        return SpotId;
    }
    public int getNumberOfSpots() {
        String directoryPath = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Spots"; // Replace this with your directory path
        File directory = new File(directoryPath);

        // Check if the given path is a directory
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            // Check if the list of files is not null
            if (files != null) {
                int fileCount = files.length;
                return fileCount;
            } else {
                return 0;
            }
        } else {
            System.out.println("The given path is not a directory.");
        }
        return 0;
    }
    public boolean IsOccupied() { return isOccupied; }

    public void occupySpot() {
        this.isOccupied = true;
    }

    public void addSpot(){
        File file = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Spots\\\{SpotId}.txt");
            while (file.exists()){
                SpotId = getNextSpotId(); //increment the id spot
                file = new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Spots\\\{SpotId}.txt");
            }
        try (PrintWriter output = new PrintWriter(file)) {
            output.println("free");
        } catch (IOException e) {
            System.out.println("An error occurred while add spot.");
        }
    }

    public void getFreeSpot() {
        String directoryPath = "C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Spots";
        File folder = new File(directoryPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                List<Integer> ArrayNumberOfSpots = new ArrayList<>();

                for (File file : files) {
                    if (file.isFile()) {
                        try (Scanner scanner = new Scanner(file)) {
                            if (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                if (line.equals("free")) {
                                    numberOfFreeSpots++;
                                    ArrayNumberOfSpots.add(Integer.parseInt(file.getName().replace(".txt", "")));
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("File not found.");
                        }
                    }
                }

                System.out.println(STR."Available spots: \{ArrayNumberOfSpots}");
                System.out.println(STR."Number of free parking spots: \{numberOfFreeSpots}");
                if (!ArrayNumberOfSpots.isEmpty()) {
                    Random random = new Random();
                    int randomAdvisedSpot = random.nextInt( ArrayNumberOfSpots.size() - 1 + 1);
                    reserveSpot(ArrayNumberOfSpots.get(randomAdvisedSpot));
                    System.out.println(STR."Park in spot:  \{ArrayNumberOfSpots.get(randomAdvisedSpot)}");
                }
            } else {
                System.out.println("No spot found.");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    public void reserveSpot(int randomAdvisedSpot) {

        File file =new File(STR."C:\\Users\\user\\Desktop\\PL2 Project Parking\\src\\Parking\\Spots\\\{randomAdvisedSpot}.txt");
        try {
            PrintWriter reserveSpot = new PrintWriter(file);
            reserveSpot.println("occupied");
            reserveSpot.close();
        }catch (Exception e){
            System.out.println("Spot id not found");
        }
    }

}


