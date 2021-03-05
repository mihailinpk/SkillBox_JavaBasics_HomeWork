public class Main   {

    public static void main(String[] args)  {

        Container container = new Container();
        container.count += 7843;

        // выводим в консоль суммы цифр чисел 12345, 10, 5059191
        System.out.println(sumDigits(12345));
        System.out.println(sumDigits(10));
        System.out.println(sumDigits(5059191));

    }

    // реализуем статический метод sumDigits, чтобы он считал сумму цифр в числе и возвращал сумму
    public static Integer sumDigits(Integer number)    {

        String numberToString = Integer.toString(number);
        int sum = 0;
        for (byte i=0; i<numberToString.length(); i++)
            // используем статический метод getNumericValue класса Character (метод получения int из char)
            sum += Character.getNumericValue(numberToString.charAt(i));

        return sum;

    }

}