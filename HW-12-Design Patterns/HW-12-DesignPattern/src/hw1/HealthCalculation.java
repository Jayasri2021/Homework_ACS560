package hw1;

import hw1.strategies.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

public class HealthCalculation {
    private final int duration;
    private final int currentPulse;
    private final int maxPulse;
    private final double calories;

    public HealthCalculation(int duration, int currentPulse, int maxPulse, double calories) {
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.calories = calories;
    }

    // Getters
    public int getDuration() { return duration; }
    public int getCurrentPulse() { return currentPulse; }
    public int getMaxPulse() { return maxPulse; }
    public double getCalories() { return calories; }

    // Calculator class that uses the strategy
    static class HealthCalculator {
        private CalculationStrategy strategy;
        
        public void setStrategy(CalculationStrategy strategy) {
            this.strategy = strategy;
        }
        
        public double executeCalculation(List<HealthCalculation> data) {
            return strategy.calculate(data);
        }
    }

    protected static List<HealthCalculation> readData() throws FileNotFoundException {
        List<HealthCalculation> data = new ArrayList<>();
        File file = new File("src/hw1/Patient_file.csv");

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("Data file not found: " + file.getPath());
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String input = reader.nextLine();
                String[] tokens = input.split(",");

                if (tokens.length == 4) {
                    try {
                        data.add(new HealthCalculation(
                            Integer.parseInt(tokens[0].trim()),
                            Integer.parseInt(tokens[1].trim()),
                            Integer.parseInt(tokens[2].trim()),
                            Double.parseDouble(tokens[3].trim())
                        ));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid data: " + input);
                    }
                } else {
                    System.out.println("Invalid data format: " + input);
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {
        try (Scanner reader = new Scanner(System.in)) {
            int userDuration = getUserInput(reader);
            processHealthData(userDuration);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static int getUserInput(Scanner reader) {
        while (true) {
            System.out.print("Enter the duration to search for maximum calories (integer value): ");
            try {
                return reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                reader.next();
            }
        }
    }

    private static void processHealthData(int userDuration) throws FileNotFoundException {
        List<HealthCalculation> data = readData();

        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        HealthCalculator calculator = new HealthCalculator();
        
        // Calculate sum of duration
        calculator.setStrategy(new DurationSumStrategy());
        double sumDuration = calculator.executeCalculation(data);
        
        // Calculate mean calories
        calculator.setStrategy(new CaloriesMeanStrategy());
        double meanCalories = calculator.executeCalculation(data);
        
        // Calculate max calories for duration
        calculator.setStrategy(new MaxCaloriesForDurationStrategy(userDuration));
        double maxCaloriesForDuration = calculator.executeCalculation(data);

        displayResults(sumDuration, meanCalories, maxCaloriesForDuration, userDuration);
    }

    private static void displayResults(double sumDuration, double meanCalories, 
                                     double maxCaloriesForDuration, int userDuration) {
        System.out.println("Sum of Duration: " + sumDuration);
        System.out.println("Mean Calories: " + meanCalories);

        if (maxCaloriesForDuration != Double.MIN_VALUE) {
            System.out.println("Maximum Calories in Duration " + userDuration + ": " + 
                             maxCaloriesForDuration);
        } else {
            System.out.println("No entries found for duration: " + userDuration);
        }
    }
}