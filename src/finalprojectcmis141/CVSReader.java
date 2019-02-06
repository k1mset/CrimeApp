package finalprojectcmis141;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * File: CVSReader.java
 * Author: Dillan Cobb
 * Date: 3/3/2018
 * Purpose: Pulls information from a .cvs file and stores it neatly into an
 * ArrayList for use by FinalProjectCMIS141.java
 */

// Imports
import finalprojectcmis141.CrimeRecord;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class CVSReader {
    // Variables to be used 
    private ArrayList<CrimeRecord> crimes = new ArrayList<CrimeRecord>();
    
    // Default CVS reader assigns crimes
    public CVSReader() {
        crimes = new ArrayList<CrimeRecord>();
    }
    
    // getter method that pulls all the .cvs file and assigns it to the 
    // constructors variables
    public void getDataFromFile(String crimeFile) {
        try {
            Scanner scan = new Scanner(new FileReader(crimeFile));
            
            String line;
            CrimeRecord crime;
            scan.nextLine();
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                String[] info = line.split(",");
                if (info != null && info.length > 0) {
                    CrimeRecord crimeRecord = new CrimeRecord();
                    
                    this.crimes.add(crimeRecord);
                    
                    int count = 0;
                    
                    int year = Integer.parseInt(info[count++]);
                    crimeRecord.setYear(year);
                    
                    int population = Integer.parseInt(info[count++]);
                    crimeRecord.setPopulation(population);

                    int violentCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setViolentCrime(violentCrime);

                    double violentCrimeRate = Double.parseDouble(info[count++]);
                    crimeRecord.setViolentCrimeRate(violentCrimeRate);

                    int murderCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setMurderCrime(murderCrime);

                    double murderRate = Double.parseDouble(info[count++]);
                    crimeRecord.setMurderRate(murderRate);

                    int rapeCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setRapeCrime(rapeCrime);

                    double rapeRate = Double.parseDouble(info[count++]);
                    crimeRecord.setRapeRate(rapeRate);

                    int robberyCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setRobberyCrime(robberyCrime);
                    
                    double robberyRate = Double.parseDouble(info[count++]);
                    crimeRecord.setRobberyRate(robberyRate);

                    int assaultCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setAssaultCrime(assaultCrime);

                    double assaultRate = Double.parseDouble(info[count++]);
                    crimeRecord.setAssaultRate(assaultRate);

                    int propertyCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setPropertyCrime(propertyCrime);

                    double propertyRate = Double.parseDouble(info[count++]);
                    crimeRecord.setPropertyRate(propertyRate);

                    int burglaryCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setBurglaryCrime(burglaryCrime);

                    double burglaryRate = Double.parseDouble(info[count++]);
                    crimeRecord.setBurglaryRate(burglaryRate);

                    int larcenyCrime = Integer.parseInt(info[count++]);
                    crimeRecord.setLarcenyCrime(larcenyCrime);

                    double larcenyRate = Double.parseDouble(info[count++]);
                    crimeRecord.setLarcenyRate(larcenyRate);

                    int vehicleTheft = Integer.parseInt(info[count++]);
                    crimeRecord.setVehicleTheft(vehicleTheft);

                    double vehicleTheftRate = Double.parseDouble(info[count++]);
                    crimeRecord.setVehicleTheftRAte(vehicleTheftRate);
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("Error:" +e.getMessage());
        }
    }
    
    // getting method that gets the population growth of the prior year and 
    // current year through a loop within the array list. then calculates the 
    // information to get a growth percentage
    public ArrayList<Double> getPopGrowthPct(int startYear, int endYear) {
        ArrayList<Double> pctGrowth = new ArrayList<>();
        // Integrate through all years and compute the growth rate by year
        // using the startYear and endYear as bookends
        if (endYear > startYear) {
            int priorYearPop = 0;
            for (CrimeRecord cr : crimes) {
                int year = cr.getYear();
                // must be greater than the year to compute growth
                if (year > startYear) {
                    int pop = cr.getPopulation();
                    double rate = (((double) (pop - priorYearPop)) / (double) 
                            priorYearPop) * 100;
                    pctGrowth.add(rate);
                }
                priorYearPop = cr.getPopulation();
            }
        }
        return pctGrowth;
    }
    
    // getter method that compares the previous year's rate to the current years 
    // rate and returns the highest rate of all years
    public int getMaxMurderRate() {
        int highestRate = 0;
        double highestMurderRate = 0.0;
        for (CrimeRecord cr : crimes) {
            double currentMurderRate = cr.getMurderRate();
            if (currentMurderRate > highestMurderRate) {
                highestRate = (int) cr.getYear();
                highestMurderRate = currentMurderRate;
            }
        }
        return highestRate;
    }
    
    // getter method that compares the previous year's rate to the current years 
    // rate and returns the lowest rate of all years
    public int getMinMurderRate() {
        int lowestRate = 0;
        double lowestMurderRate = 99999.99;
        for (CrimeRecord cr : crimes) {
            double currentMurderRate = cr.getMurderRate();
            if (currentMurderRate < lowestMurderRate) {
                lowestRate = (int) cr.getYear();
                lowestMurderRate = currentMurderRate;
            }
        }
        return lowestRate;
    }
    
    // getter method that compares the previous year's rate to the current years 
    // rate and returns the highest rate of all years
    public int getMaxRobberyRate() {
        int highestRate = 0;
        double highestRobberyRate = 0.0;
        for (CrimeRecord cr : crimes) {
            double currentRobberyRate = cr.getRobberyRate();
            if (currentRobberyRate > highestRobberyRate) {
                highestRate = (int) cr.getYear();
                highestRobberyRate = cr.getRobberyRate();
            }
        }
        return highestRate;
    }
    
    // getter method that compares the previous year's rate to the current years 
    // rate and returns the lowest rate of all years
    public int getMinRobberyRate() {
        int lowestRate = 0;
        double lowestRobberyRate = 99999.99;
        for (CrimeRecord cr : crimes) {
            double currentRobberyRate = cr.getRobberyRate();
            if (currentRobberyRate < lowestRobberyRate) {
                lowestRate = (int) cr.getYear();
                lowestRobberyRate = cr.getRobberyRate();
            }
        }
        return lowestRate;
    }
}
