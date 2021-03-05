import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static java.util.Calendar.*;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");

    public static void main(String[] args)
    {

        System.out.print("Задание №1\n\n");
        ArrayList<Employee> staff = loadStaffFromFile();

        int requiredYear = 2017;
        staff.stream().filter(e -> {
            Calendar markOfTimeCalendar = Calendar.getInstance();
            markOfTimeCalendar.setTime(e.getWorkStart());
            return markOfTimeCalendar.get(YEAR) == requiredYear;
        }).max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

        System.out.print("\n=====================================================");

        System.out.print("\n\nЗадание №2\n\n");
        Calendar calendarMarkTimeNow = new GregorianCalendar();
        Calendar calendarMarkPlusTwoHours = new GregorianCalendar();
        calendarMarkPlusTwoHours.add(HOUR, 2);
        Airport.getInstance()
            .getTerminals()
            .stream()
            .flatMap(terminal -> terminal.getFlights().stream())
            .filter(
                flight -> flight.getType().equals(Flight.Type.ARRIVAL) &&
                    flight.getDate().getTime() >= calendarMarkTimeNow.getTimeInMillis() &&
                    flight.getDate().getTime() <= calendarMarkPlusTwoHours.getTimeInMillis()
            )
            .forEach(flight -> System.out.println(HOUR_FORMAT.format(flight.getDate()) + " / " + flight.getAircraft()));

    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}