import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Введите полное имя: ");
        Scanner scanner = new Scanner(System.in);
        String inputFullName = scanner.nextLine().trim();

        byte spacesCount = 0;
        for (int i=0; i<inputFullName.toCharArray().length; i++)    {
            spacesCount += (inputFullName.charAt(i) == ' ' ? 1 : 0);
        }

        if (spacesCount == 2)   {

            String surname = inputFullName.substring(0, inputFullName.indexOf(' '));
            String name = inputFullName.substring(inputFullName.indexOf(' '), inputFullName.lastIndexOf(' ')).trim();
            String patronymic = inputFullName.substring(inputFullName.lastIndexOf(' ')).trim();

            System.out.println("Фамилия:\t" + surname);
            System.out.println("Имя:\t\t" + name);
            System.out.println("Отчество:\t" + patronymic);

        }   else    {
            System.out.println("Вы ввели некорректные данные...");
        }

    }

}