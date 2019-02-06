/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectcmis141;

/**
 * File: FinalProjectCMIS141.java
 * Author: Dillan Cobb
 * Date: 3/3/2018
 * Purpose: To search upon users request inside of a .cvs file to find
 * accurate crime information for the user.
 */

// Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class FinalProjectCMIS141 {
    
    public static void main(String[] args) {
        // Variables for use
        boolean quit = false;
        String selection;
        String crimeFile = args[0];
        long startTime = System.nanoTime();
        
        // Constructors
        CVSReader crimeDatabase = new CVSReader();
        Scanner scannerIn = new Scanner(System.in);
        DecimalFormat format = new DecimalFormat("##.00");
        
        // getter to load the data from the file
        crimeDatabase.getDataFromFile(crimeFile);
        
        // While loop that will continuesly loop at each entry of data into the 
        // scanner until the quit boolean is true
        while (quit == false) {
            System.out.println(" *** US Crime Statistics *** ");
            System.out.println("");
            System.out.println("Enter the number of the questions you want " +
                    "awnsered. Enter Q to exit the program.");
            System.out.println("1. What were the percentages in population " +
                    "growth for each consecutive year from 1994 - 2013");
            System.out.println("2. What year was the Murder rate the highest");
            System.out.println("3. What year was the Murder rate the lowest");
            System.out.println("4. What year was the Robbery rate the highest");
            System.out.println("5. What year was the Robbery rate the lowest");
            System.out.println("Press Q to quit the program");
            selection = scannerIn.next();
            if (selection.equals("1")) {
                int startYear = 1994;
                int endYear = 2013;
                ArrayList<Double> pctGrowth = crimeDatabase.getPopGrowthPct(
                        startYear, endYear);
                for (double rate : pctGrowth) {
                    System.out.println("Growth rate between " + startYear + 
                            " and " + ++startYear + " = " + rate + "%");
                }
            }
            else if (selection.equals("2")) {
                int highestYear = crimeDatabase.getMaxMurderRate();
                System.out.println("The year with the highest murder rate is " +
                        highestYear + ".");
            }
            else if (selection.equals("3")) {
                int lowestYear = crimeDatabase.getMinMurderRate();
                System.out.println("The year with the lowest murder rate is " +
                        lowestYear + ".");
            }
            else if (selection.equals("4")) {
                int highestYear = crimeDatabase.getMaxRobberyRate();
                System.out.println("The year with the highest robbery rate is " 
                        + highestYear + ".");
            }
            else if (selection.equals("5")) {
                int lowestYear = crimeDatabase.getMinRobberyRate();
                System.out.println("The year with the lowest robbery rate is " 
                        + lowestYear + ".");
            }
            else if (selection.toLowerCase().equals("q")) {
                quit = true;
            }
            else {
                System.out.println("Please enter a valid input");
            }
        }
        
        // Calculates the eclapsed amount of time by assigning a time variable 
        // at the beginning of the start of the program, and creating another
        // at the end of the programs life. Subtracts the larger time by
        // the smaller town.
        long endTime = System.nanoTime();
        endTime = endTime - startTime;
        double eclapsedTime = (double)endTime / 1000000000;
        System.out.println(" *** Thank you for using US Crime Statistics *** ");
        System.out.println("Your eclapsed program time: " + 
                format.format(eclapsedTime) + " seconds.");
       
        System.exit(0);
    }
}
