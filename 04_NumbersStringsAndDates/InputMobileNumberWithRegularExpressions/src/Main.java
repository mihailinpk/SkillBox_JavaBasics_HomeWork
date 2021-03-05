import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Введите номер абонента:\t\t\t\t ");
            String inputPhoneNumber = reader.readLine().replaceAll("[^\\d]", "");

            switch (inputPhoneNumber.length())  {

                case 11:
                    if (!inputPhoneNumber.matches("[78]{1}\\d+"))   {
                        throw new Exception();
                    }   else    {
                        inputPhoneNumber = inputPhoneNumber.substring(1);
                    }
                    break;
                case 10:
                    if (!inputPhoneNumber.matches("\\d+")) {
                        throw new Exception();
                    }
                    break;
                default:
                    throw new Exception();

            }
            System.out.println("Приведенный к единому формату номер: " + formattingThePhoneNumber(inputPhoneNumber));

        }
        catch (Exception ex)    {
            System.out.println("Неверный формат номера");
        }

    }

    private static String formattingThePhoneNumber(String inputPhoneNumber) {
        return inputPhoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d+)", "+7($1)$2$3");
    }

}