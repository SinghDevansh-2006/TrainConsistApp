package com.train.app;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("\nSystem ready for operations ...");
    }
}