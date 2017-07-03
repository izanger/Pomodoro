/**
 * Created by ianzanger on 6/24/17.
 */
import java.util.Scanner;

public class Pomodoro {

    //default settings, with lengths in minutes.
    public static double defaultPomLength = 25.0;
    public static double defaultShortBreakLength = 5.0;
    public static double defaultLongBreakLength = 15.0;

    public static int defaultNumPoms = 4;
    public static int defaultNumCycles = 1;

    public static void main(String[] args) {
        double pomLength = -1;
        double shortBreakLength = -1;
        double longBreakLength = -1;
        int numPoms = -1;
        int numCycles = -1;

        Scanner s = new Scanner(System.in);
        System.out.println("Press <Enter> for a standard Pomodoro cycle, or type a letter for a custom cycle.");
        String input = s.nextLine();

        if(input.length() == 0) {
            try {
                cyclePomodoro(defaultPomLength, defaultShortBreakLength, defaultLongBreakLength, defaultNumPoms, defaultNumCycles);
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception");
            }
        } else {
            //pomLength
            System.out.println("Enter length of work periods, in minutes: ");
            input = s.nextLine();

            //check for validity of input
            if( !isValidNumber(input) || Double.parseDouble(input) <= 0.0 ){
                do {
                    System.out.println("Please enter a decimal number greater than zero.");
                    input = s.nextLine();
                    if(isValidNumber(input) && Double.parseDouble(input) > 0.0){
                        pomLength = Double.parseDouble(input);
                    }

                } while(pomLength == -1);
            } else {
                pomLength = Double.parseDouble(input);
            }

            //shortBreakLength
            System.out.println("Enter length of short break, in minutes: ");
            input = s.nextLine();

            if( !isValidNumber(input) || Double.parseDouble(input) <= 0.0 ){
                do {
                    System.out.println("Please enter a decimal number greater than zero.");
                    input = s.nextLine();
                    if(isValidNumber(input) && Double.parseDouble(input) > 0.0){
                        shortBreakLength = Double.parseDouble(input);
                    }

                } while(shortBreakLength == -1);
            } else {
                shortBreakLength = Double.parseDouble(input);
            }

            //longBreakLength
            System.out.println("Enter length of long break, in minutes: ");
            input = s.nextLine();

            if( !isValidNumber(input) || Double.parseDouble(input) <= 0.0 ){
                do {
                    System.out.println("Please enter a decimal number greater than zero.");
                    input = s.nextLine();
                    if(isValidNumber(input) && Double.parseDouble(input) > 0.0){
                        longBreakLength = Double.parseDouble(input);
                    }

                } while(longBreakLength == -1);
            } else {
                longBreakLength = Double.parseDouble(input);
            }

            //numPoms
            System.out.println("Enter the number of work periods to complete before taking a long break:");
            input = s.nextLine();

            if( !isValidNumber(input) || (Double.parseDouble(input) < 1.0) ){
                do {
                    System.out.println("Please enter an integer number greater than zero.");
                    input = s.nextLine();
                    if(isValidNumber(input) && Double.parseDouble(input) >= 1.0){
                        numPoms = (int) Double.parseDouble(input);
                    }

                } while(numPoms == -1);
            } else {
                numPoms = (int) Double.parseDouble(input);
            }

            //numCycles
            System.out.println("Enter the number of complete pomodoro cycles (first work period through long break) to complete without interruption: ");
            input = s.nextLine();

            if( !isValidNumber(input) || (Double.parseDouble(input) < 1.0) ){
                do {
                    System.out.println("Please enter an integer number greater than zero.");
                    input = s.nextLine();
                    if(isValidNumber(input) && Double.parseDouble(input) >= 1.0){
                        numCycles = (int) Double.parseDouble(input);
                    }

                } while(numCycles == -1);
            } else {
                numCycles = (int) Double.parseDouble(input);
            }



            try {
                cyclePomodoro(pomLength, shortBreakLength, longBreakLength, numPoms, numCycles);
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception");
            }

        }

    }

    public static void cyclePomodoro(double pomLength, double shortBreakLength, double longBreakLength, int numPoms, int numCycles) throws InterruptedException {
        for(int j = 0; j < numCycles; j++) {
            System.out.println(">>Cycle " + (j+1) + " of " + numCycles);
            for (int i = 0; i < numPoms; i++) {
                System.out.println("\tStarting work timer for " + pomLength + " minutes (" + (i+1) + "/" + (numPoms) + ")");
                Thread.sleep((long) (pomLength * 1000 * 60));
                System.out.println("\tWork period completed. ");

                if (i == (numPoms - 1)) {
                    System.out.println("\t\t\tStarting long break timer for " + longBreakLength + " minutes.");
                    Thread.sleep((long) (longBreakLength * 1000 * 60));
                    System.out.println("\t\t\tLong break period completed. ");
                } else {
                    System.out.println("\t\tStarting short break timer for " + shortBreakLength + " minutes.");
                    Thread.sleep((long) (shortBreakLength * 1000 * 60));
                    System.out.println("\t\tShort break period completed. ");
                }
            }

        }

        Scanner s = new Scanner(System.in);
        System.out.println("Type: " +
                "\n\t > <Enter> to start another set of Pomodoro cycles with the same settings " +
                "\n\t > A number for that many more cycles with the same settings " +
                "\n\t > \"Default\" for a cycle with default settings " +
                "\n\t > Any other key to quit");

        String input = s.nextLine();
        if (input.length() == 0) {
            cyclePomodoro(pomLength, shortBreakLength, longBreakLength, numPoms, numCycles);
        } else if(input.toLowerCase().equals("default")){
            cyclePomodoro(defaultPomLength, defaultShortBreakLength, defaultLongBreakLength, defaultNumPoms, defaultNumCycles);
        } else if(isValidNumber(input)){
            if(Double.parseDouble(input) >= 1.0){
                cyclePomodoro(pomLength, shortBreakLength, longBreakLength, numPoms, (int) Double.parseDouble(input));
            }
        }
    }

    public static boolean isValidNumber (String num) {
        try {
            Double.parseDouble(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
