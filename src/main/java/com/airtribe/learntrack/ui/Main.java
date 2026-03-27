package com.airtribe.learntrack.ui;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "0" -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Option not recognized. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("==== LearnTrack Menu ====");
        System.out.println("0) Exit");
        System.out.print("Select option: ");
    }


}

