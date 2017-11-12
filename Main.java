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

        System.out.println("Enter email: ");
        String email = sc.next();
        
        String prior = "";
        System.out.println("Port: " + port);
        
        Arduino arduino = new Arduino(port, baudRate);
        arduino.openConnection();
        
        String z = "a"
        
        while (true) {
            String input = arduino.serialRead(1);
            if (input == a) {
                System.out.println("Serial input detected");
                try {
                    Mail.send(email, "You clicked a button!", "Congratulations!  You have graduated from the school of clicking buttons!");
                } catch (Throwable err) {
                    System.out.println("Error sending email: " + err);
                    break;
                }
            }
            prior = "";
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Throwable err) {
                System.out.println("Error setting up serial conection" + err);
                break;
            }
        }
    }
}
