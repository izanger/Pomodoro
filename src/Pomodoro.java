/**
 * Created by ianzanger on 6/24/17.
 */
public class Pomodoro {
    public static void main(String[] args) {
        try{

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

        } catch (InterruptedException e) {
            System.err.println("Interrupted exception");
        }
    }
}
