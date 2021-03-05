public class Main {

    public static void main(String[] args) {

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

    }

    private static String searchAndReplaceDiamonds(String text, String placeholder) {

        int numberOfLeftBracket = text.indexOf('<');
        int numberOfRightBracket = text.lastIndexOf('>')+1;
        if (numberOfLeftBracket < numberOfRightBracket) {
            return text.substring(0, text.indexOf('<')) + placeholder + text.substring(text.lastIndexOf('>')+1);
        }   else    {
            return "error";
        }

    }

}