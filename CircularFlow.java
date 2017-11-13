import arduino.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.Scanner;

public class CircularFlow {
    static String PORT = "";
    static int BAUD = 9600;
    
    private static String getPort(String fileName) {
        File f = new File(fileName);
        String port = null;
        try {
            Scanner sc = new Scanner(f);
            port = sc.nextLine();
        } catch (Throwable err) {
            System.out.println(err);
        }
        return port;
    }
    
    public static void main(String[] args) {
        // When new byte recieved, send byte to Arduino
        System.out.println("Circular flow program initiated");
        PORT = getPort("port.txt");
        Arduino arduino = new Arduino(PORT, BAUD);
        arduino.openConnection();
        while (true) {
            String input = arduino.serialRead(1);
            if (!input.equals("")) {
                System.out.println("We have a signal!");
            }
            if (input.equals("a")) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    arduino.serialWrite("b");
                } catch (Throwable err) {
                    System.out.println(err);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Throwable err) {
                System.out.println(err);
            }
        }
    }
}