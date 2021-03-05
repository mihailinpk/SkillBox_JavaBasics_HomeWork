import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))   {

            System.out.print("Введите полное имя: ");
            String inputFullName = reader.readLine();

            String[] splittedFullName = inputFullName.split("\\s+");

            if (!interedDataIsCorrected(splittedFullName))  {
                throw new Exception();
            }

            System.out.println("Фамилия:\t" + splittedFullName[0]);
            System.out.println("Имя:\t\t" + splittedFullName[1]);
            System.out.println("Отчество:\t" + splittedFullName[2]);

        }
        catch (Exception ex)    {
            System.out.print("Вы ввели некорректное значение");
        }

    }

    private static boolean interedDataIsCorrected(String[] splittedFullName)  {
        if (splittedFullName.length != 3)   {
            return false;
        }   else    {
            for(int i=0; i<=2; i++) {
                if (!splittedFullName[i].matches("[А-Я]{1}[а-я]+"))    {
                    return false;
                }
            }
        }
        return true;
    }
}