import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=Europe/Moscow";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT course_name, COUNT(course_name)/(MAX(month(subscription_date))-" +
                            "MIN(month(subscription_date))+1) AS average_number_of_purchases FROM purchaselist " +
                            "WHERE YEAR(subscription_date)=2018 GROUP BY course_name;"
            );
            while (resultSet.next())    {
                System.out.println(
                        stringAligning(resultSet.getString(1), 34) +
                                " | " + resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex)  {
            ex.printStackTrace();
        }

    }

    public static String stringAligning(String stringToAlign, int lengthAlignment) {
        while(stringToAlign.length() < lengthAlignment)  {
            stringToAlign = stringToAlign + " ";
        }
        return stringToAlign;
    }

}
