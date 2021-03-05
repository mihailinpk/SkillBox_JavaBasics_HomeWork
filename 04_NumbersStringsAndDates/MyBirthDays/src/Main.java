import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        try {

            System.out.print("Введите дату рождения в формате дд/мм/гггг: ");
            String inputBirthday = keyboard.next();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - EEEE", new Locale("ru"));
            Calendar calendarLastBirthDays = Calendar.getInstance();
            calendarLastBirthDays.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(inputBirthday));
            Calendar calendarCurrentDateTime = Calendar.getInstance();
            int countOfBirthday = 0;

            while (calendarLastBirthDays.before(calendarCurrentDateTime))   {

                System.out.printf("%s - %s\n",
                        countOfBirthday++,
                        dateFormat.format(calendarLastBirthDays.getTime()));
                calendarLastBirthDays.add(Calendar.YEAR, 1);

            }

        }   catch (Exception ex)    {
            System.out.println("Вы ввели некорректные данные...");
        }

    }

}