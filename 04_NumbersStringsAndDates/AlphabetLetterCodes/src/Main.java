public class Main {

    public static void main(String[] args) {

        System.out.println("-----===== Латиница  =====-----");
        printLettersAndCodes('A', 'z', 'Z', 'a');

        System.out.println("\n-----===== Кириллица =====-----");
        printLettersAndCodes('А', 'я', 0, 0);

    }

    private static void printLettersAndCodes(
            int beginIntervalLetterCode,
            int endIntervalLetterCode,
            int beginPassIntervalLetterCode,
            int endPassIntervalLetterCode
    )   {

        for(int i = beginIntervalLetterCode; i<= endIntervalLetterCode; i++)    {
            if (i<=beginPassIntervalLetterCode || i>=endPassIntervalLetterCode)
                System.out.println("символ: " + (char)i + " | код(Unicode): " + i);
        }

    }

}