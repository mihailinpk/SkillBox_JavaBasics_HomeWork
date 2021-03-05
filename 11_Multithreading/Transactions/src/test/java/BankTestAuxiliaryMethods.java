import java.util.Random;

public class BankTestAuxiliaryMethods {

    private static String[] accNumbers = {"acc1522", "acc3582", "acc5478", "acc6431", "acc9173", "acc6842"};
    private static Random random = new Random();

    public static String getRandomAccNumber() {
        return accNumbers[random.nextInt(6)];
    }

}
