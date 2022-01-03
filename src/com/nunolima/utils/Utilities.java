package com.nunolima.utils;

import java.util.Scanner;

public class Utilities {

    private static final Scanner pressEnter = new Scanner(System.in);

    public static final Scanner sc = new Scanner(System.in);


    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33;1m";
    public static final String BLACK = "\u001B[30m";


    public static final String CLEAR = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public static final String YELLOW_BACKGROUND = "\u001b[43;1m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String RED_BACKGROUND = "\u001B[41;1m";


    public static final String RESET = "\u001B[0m";

    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...\n");
        pressEnter.nextLine();
    }

    public static int getLargestNumber(int[] array) {
        int indexSaver = 0;
        int biggestNumber = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > biggestNumber) {
                biggestNumber = array[i];
                indexSaver = i;
            }
        }


        return indexSaver;
    }

    public static String checkIfValidInput(Integer minimumAcceptable, Integer maximumAcceptable) {
        String choice = null;
        boolean invalid = true;
        while (invalid) {
            choice = sc.nextLine();
            if (choice.matches(".*[0-9]")) {
                if (Integer.parseInt(choice) >= minimumAcceptable && Integer.parseInt(choice) <= maximumAcceptable) {
                    invalid = false;
                }else {
                    System.out.println("\nInvalid Input. Try again:");

                }
            } else {
                System.out.println("\nInvalid Input. Try again:");
            }
        }
        return choice;
    }
}
