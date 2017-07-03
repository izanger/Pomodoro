/**
 * Created by ianzanger on 6/24/17.
 */
import java.util.Scanner;

public class Pomodoro {
    public static int defaultPomLength = 25;
    public static int defaultShortBreakLength = 5;
    public static int defaultLongBreakLength = 15;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Press <Enter> for a standard Pomodoro cycle, or type a letter for a custom cycle.");
        String input = s.nextLine();

        if(input.length() == 0) {
            try {
                cyclePomodoro(defaultPomLength, defaultShortBreakLength, defaultLongBreakLength);
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception");
            }
        } else {

        }
    }

    public static void cyclePomodoro(int pomLength, int shortBreakLength, int longBreakLength) throws InterruptedException {

        for(int i = 0; i < 4; i++) {
            if(i > 0) {
                System.out.print("5 minutes elapsed. ");
            }

            System.out.println("Starting timer for 25 minutes.");
            Thread.sleep(5 * 1000); //replace with 25min when done testing: 25 * 60 * 1000

            if(i == 3){
                System.out.println("25 minutes elapsed. Starting timer for 15 minute break.");
                Thread.sleep(10 * 1000); //replace with 15 * 60 * 1000
            } else {
                System.out.println("25 minutes elapsed. Starting timer for 5 minute break.");
                Thread.sleep(1000 * 5); //replace with 5 * 60 * 1000
            }
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Press <Enter> to start a new Pomodoro cycle with the same settings, or type a letter to quit.");
        String input = s.nextLine();

        if(input.length() == 0){
            cyclePomodoro(pomLength, shortBreakLength, longBreakLength);
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
