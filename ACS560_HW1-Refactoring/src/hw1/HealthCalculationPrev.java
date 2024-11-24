package hw1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

public class HealthCalculationPrev 
{
    int duration;
    int currentPulse;
    int maxPulse;
    double caloriesBurned;

    public HealthCalculationPrev(int duration, int currentPulse, int maxPulse, double caloriesBurned) 
    {
        this.duration = duration;
        this.currentPulse = currentPulse;
        this.maxPulse = maxPulse;
        this.caloriesBurned = caloriesBurned;
    }

    public int getDuration() 
    {
        return duration;
    }

    public int getCurrentPulse() 
    {
        return currentPulse;
    }

    public int getMaxPulse() 
    {
        return maxPulse;
    }

    public double getCalories() 
    {
        return caloriesBurned;
    }

    public static void main(String[] args) 
    {
        Scanner reader = new Scanner(System.in);

        int userDuration = 0;
        boolean validInput = false;

        while (!validInput) 
        {
            System.out.print("Enter the duration to search for maximum calories in(integer value): ");
            try 
            {
                userDuration = reader.nextInt();
                validInput = true;
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid input.");
                reader.next(); 
            }
        }

        try 
        {
            List<HealthCalculationPrev> data = read_data();

            if (data.isEmpty()) 
            {
                System.out.println("No data found.");
                return;
            }

            double sum_d = 0;
            double sum_c = 0;
            double maxcal_dur = Double.MIN_VALUE;

            for (HealthCalculationPrev health : data) 
            {
                sum_d += health.getDuration();
                sum_c += health.getCalories();
                
                if (health.getDuration() == userDuration && health.getCalories() > maxcal_dur) 
                {
                    maxcal_dur = health.getCalories();
                }
            }

            double avgcal = sum_c / data.size();

            System.out.println("Sum of Duration: " + sum_d);
            System.out.println("Mean Calories: " + avgcal);

            if (maxcal_dur != Double.MIN_VALUE) 
            {
                System.out.println("Maximum Calories in Duration " + userDuration + ": " + maxcal_dur);
            } 
            else 
            {
                System.out.println("No entries with Duration found.");
            }

        } 
        catch (FileNotFoundException e) 
        {
            System.out.println(e);
        } 
    }

    private static List<HealthCalculationPrev> read_data() throws FileNotFoundException 
    {
        List<HealthCalculationPrev> data = new ArrayList<>();

        File file = new File("src/Patient_file.csv");

        if (!file.exists() || !file.isFile()) 
        {
            throw new FileNotFoundException("Fata file not found.");
        }

        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) 
        {
            String input = reader.nextLine();
            String[] tokens = input.split(",");

            if (tokens.length == 4)
            {
                try 
                {
                    int duration = Integer.parseInt(tokens[0]);
                    int c_pulse = Integer.parseInt(tokens[1]);
                    int max_pulse = Integer.parseInt(tokens[2]);
                    double calo = Double.parseDouble(tokens[3]);

                    HealthCalculationPrev health = new HealthCalculationPrev(duration, c_pulse, max_pulse, calo);
                    data.add(health);
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("Invalid data: " + input);
                }
            } 
            else 
            {
                System.out.println("Invalid data: " + input);
            }
        }
        reader.close();

        return data;
    }
}
