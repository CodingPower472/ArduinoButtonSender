import arduino.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.*;

public class Main {
    private static int baudRate = 9600;
    
    public static void main(String[] args) {
        String email = "";
        String pswd = "";
        try {
            File f = new File("creds.txt");
            Scanner fr = new Scanner(f);
            String currentRead = fr.nextLine();
            String[] splitted = currentRead.split("; ");
            email = splitted[0];
            pswd = splitted[1];
        } catch (Throwable err) {
            System.out.println("Error reading credentials: " + err);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Up and at 'em!");
        System.out.println("Enter port: ");
        String port = sc.next();
        System.out.println("Enter email: ");
        String emailTo = sc.next();
        Arduino arduino = new Arduino(port, baudRate);
        arduino.openConnection();
        while (true) {
            String input = arduino.serialRead(1);
            if (input != "") {
                System.out.println("Serial input detected");
                try {
                    Mail.send(email, pswd, emailTo, "You clicked a button!", "Congratulations!  You have graduated from the school of clicking buttons!");
                } catch (Throwable err) {
                    System.out.println("Error sending email: " + err);
                    break;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Throwable err) {
                System.out.println("Error setting up serial conection" + err);
                break;
            }
        }
    }
}