
import java.util.Scanner;
import server.Server;

public class Main {

    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name: ");
        String username = scanner.next();
        System.out.println("Welkom " + username);

        new Server();
    }
}
