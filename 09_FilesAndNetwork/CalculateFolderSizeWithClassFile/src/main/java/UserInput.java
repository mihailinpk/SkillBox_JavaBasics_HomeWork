import java.util.Scanner;

public class UserInput {

    private static final Scanner keyboard = new Scanner(System.in);

    public static String getLine() {
        return keyboard.nextLine();
    }

}