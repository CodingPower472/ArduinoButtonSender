import arduino.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Main {
    private static String portDescription = "/dev/ttyACM1";
    private static int baudRate = 9600;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arduino arduino = new Arduino(portDescription, baudRate);
        arduino.openConnection();
        System.out.println("Up and at 'em!");
        System.out.println("Enter port: ");
        String port = sc.next();
        portDescription = port;
        String email = sc.next();
        String prior = "";
        System.out.println("Port: " + port);
        while (true) {
            String input = arduino.serialRead();
            System.out.println("Serial input detected");
            if (input != prior) {
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