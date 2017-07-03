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

        }
    }

    public static void cyclePomodoro(double pomLength, double shortBreakLength, double longBreakLength, int numPoms) throws InterruptedException {

        for(int i = 0; i < numPoms; i++) {
            if(i > 0) {
                System.out.print(shortBreakLength + " minutes elapsed. ");
            }

            System.out.println("Starting timer for " + pomLength + " minutes.");
            Thread.sleep((long) pomLength * 1000 * 60);

            if(i == (numPoms - 1) ) {
                System.out.println(pomLength + " minutes elapsed. Starting timer for " + longBreakLength + " minute break.");
                Thread.sleep((long) longBreakLength * 1000 *  60);
            } else {
                System.out.println(pomLength + " minutes elapsed. Starting timer for " + shortBreakLength + " minute break.");
                Thread.sleep((long) shortBreakLength * 1000 * 60 );
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
