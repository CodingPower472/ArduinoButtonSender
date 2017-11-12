import arduino.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Main {
    private static String portDescription = "/dev/ttyACM1";
    private static int baudRate = 9600;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Up and at 'em!");
        System.out.println("Enter port: ");
        String port = sc.next();
        portDescription = port;
        System.out.println("Enter email: ");
        String email = sc.next();
        String prior = "";
        System.out.println("Port: " + port);
        Arduino arduino = new Arduino(portDescription, baudRate);
        arduino.openConnection();
        while (true) {
            String input = arduino.serialRead();
            if (input != prior) {
                System.out.println("Serial input detected");
                try {
                    Mail.send(email, "You clicked a button!", "Congratulations!  You have graduated from the school of clicking buttons!");
                } catch (Throwable err) {
                    System.out.println("Error sending email: " + err);
                    break;
                }
            }
            prior = input;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Throwable err) {
                System.out.println("Error setting up serial conection" + err);
                break;
            }
        }
    }
}