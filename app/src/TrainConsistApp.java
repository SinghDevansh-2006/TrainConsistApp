package com.train.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("=== Train Consist Management App ===");
        System.out.println("=================================");

        List<String> trainConsist = new ArrayList<>();

        System.out.println("Train initialized successfully ...");
        System.out.println("Initial Bogie Count : " + trainConsist.size());
        System.out.println("Current Train Consist : " + trainConsist);

        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");

        System.out.println("\nAfter Adding Bogies:");
        System.out.println("Train Consist : " + trainConsist);

        trainConsist.remove("AC Chair");

        System.out.println("\nAfter Removing AC Chair:");
        System.out.println("Train Consist : " + trainConsist);

        boolean exists = trainConsist.contains("Sleeper");

        System.out.println("\nChecking if Sleeper exists:");
        System.out.println("Sleeper Present : " + exists);

        System.out.println("\nFinal Train Consist : " + trainConsist);

        System.out.println("\n--- UC3: Unique Bogie ID Tracking ---");

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");
        bogieIds.add("BG102");

        System.out.println("Bogie IDs After Insertion:");
        System.out.println(bogieIds);

        System.out.println("\nNote:");
        System.out.println("Duplicates are automatically ignored by HashSet.");
        System.out.println("All unique bogies are maintained.");

        System.out.println("\nSystem ready for operations ...");
    }
}