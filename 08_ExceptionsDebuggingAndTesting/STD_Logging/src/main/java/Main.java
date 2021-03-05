import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {

    private static final Logger rootLogger = LogManager.getRootLogger();
    private static final Marker enteredDataMarker = MarkerManager.getMarker("entered_data");
    private static final Marker errorsMarker = MarkerManager.getMarker("debug");

    public static void main(String[] args) {

        while (true)    {

            System.out.print("Please enter data: ");
            String line = UserInput.getLine();
            rootLogger.info(enteredDataMarker, "User entered line: " + line);
            if (line.equals("exit"))
                break;
            int result;
            try {
                result = Integer.parseInt(line);
            }
            catch (Exception ex)    {
                System.out.println("You entered incorrect data :(");
                rootLogger.debug(errorsMarker, "User entered line: "/* + line + "\n" + ExceptionUtils.getStackTrace(ex)*/);
                continue;
            }
            System.out.println("Transformed data: " + result);

        }

    }

}