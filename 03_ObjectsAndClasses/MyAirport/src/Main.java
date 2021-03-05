import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // создаем экземпляр класса аэропорт
        Airport airport = Airport.getInstance();

        // получаем список самолетов
        List<Aircraft> allAircrafts = airport.getAllAircrafts();

        // распечатываем число самолетов в аэропорту
        System.out.println("Число самолетов в аэропорту: " + allAircrafts.size());

    }

}