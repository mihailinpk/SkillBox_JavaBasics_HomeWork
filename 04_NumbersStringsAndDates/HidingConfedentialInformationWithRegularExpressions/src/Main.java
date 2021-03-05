public class Main {

    public static void main(String[] args) {

        String creditCardNumber = "Номер кредитной карты <4008 1234 5678> 8912";
        System.out.println(searchAndReplaceDiamonds(creditCardNumber, "***"));

    }

    private static String searchAndReplaceDiamonds(String text, String placeholder) {

        return text.matches(".*<.+>.*") ? text.replaceAll("<.+>", placeholder) : "Данные некорректны";

    }

}