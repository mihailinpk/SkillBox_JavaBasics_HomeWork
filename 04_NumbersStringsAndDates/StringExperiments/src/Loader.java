
public class Loader
{
    public static void main(String[] args)
    {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);

        int vasyaSalary = Integer.parseInt(text.substring(text.indexOf("заработал") + 9, text.indexOf("рублей, Петя")).trim());
        int petyaSalary = Integer.parseInt(text.substring(text.indexOf("-") + 1, text.indexOf("рубля, а Маша")).trim());
        int mashaSalary = Integer.parseInt(text.substring(text.indexOf("Маша -") + 6, text.lastIndexOf("рублей")).trim());
        int totalSalary = vasyaSalary + petyaSalary + mashaSalary;

        System.out.print("Все вместе заработали: " + totalSalary + " рублей");

    }
}