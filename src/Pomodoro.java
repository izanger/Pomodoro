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

    public static void main(String[] args) {
        double pomLength = -1;
        double shortBreakLength = -1;
        double longBreakLength = -1;
        int numPoms = -1;

        Scanner s = new Scanner(System.in);
        System.out.println("Press <Enter> for a standard Pomodoro cycle, or type a letter for a custom cycle.");
        String input = s.nextLine();

        if(input.length() == 0) {
            try {
                cyclePomodoro(defaultPomLength, defaultShortBreakLength, defaultLongBreakLength, defaultNumPoms);
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
                System.out.println("MADE IT");
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

            try {
                cyclePomodoro(pomLength, shortBreakLength, longBreakLength, numPoms);
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception");
            }

        }

    }

    public static void cyclePomodoro(double pomLength, double shortBreakLength, double longBreakLength, int numPoms) throws InterruptedException {


        System.out.println("NUM POMS: " + numPoms);
        for(int i = 0; i < numPoms; i++) {
            if(i > 0) {
                System.out.print(shortBreakLength + " minutes elapsed. ");
            }

            System.out.println("Starting timer for " + pomLength + " minutes.");
            Thread.sleep((long) pomLength * 1000 * 60);

            if(i == (numPoms - 1) ) {
                System.out.println(pomLength + " minutes elapsed. Starting timer for " + longBreakLength + " minute break.");
                Thread.sleep((long) longBreakLength * 1000 *  60);
                System.out.println(pomLength + " minutes elapsed. Starting timer for " + shortBreakLength + " minute break.");
                Thread.sleep((long) shortBreakLength * 1000 * 60);
            }
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Press <Enter> to start a new Pomodoro cycle with the same settings, or type a letter to quit.");
        String input = s.nextLine();

        if(input.length() == 0){
            cyclePomodoro(pomLength, shortBreakLength, longBreakLength, numPoms);
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
