import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RouteCalculatorTest extends Assert {

    private static String dataFile = "src/test/resources/map.json";

    private static StationIndex testStationIndex;
    private static RouteCalculator testRouteCalculator;

    List<Station> route;

    private static Station station1;
    private static Station station2;
    private static Station station3;
    private static Station station4;
    private static Station station5;

    private final static double DELTA = 0.001;

    @Before
    public void setUpRouteCalculatorTest()  {

        testStationIndex = new StationIndex();
        testRouteCalculator = getRouteCalculator();

        station1 = testStationIndex.getStation("Девяткино");
        station2 = testStationIndex.getStation("Ленинский проспект");
        station3 = testStationIndex.getStation("Рыбацкое");
        station4 = testStationIndex.getStation("Беговая");
        station5 = testStationIndex.getStation("Комендантский проспект");

    }

    @Test
    public void testGetShortestRouteWithRouteOnTheLine()    {
        route = testRouteCalculator.getShortestRoute(station1, station2);
        System.out.println("Маршрут без пересадок:");
        printRoute(route);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 2.5 * (route.size() -1);
        double delta = 0.001;
        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testGetShortestRouteWithRouteWithOneConnection()    {
        route = testRouteCalculator.getShortestRoute(station1, station3);
        System.out.println("Маршрут с одной пересадкой:");
        RouteCalculator.printRoute(route);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 2.5 * (route.size() -1) + 1.0;
        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testGetShortestRouteWithRouteWithTwoConnection()    {
        route = testRouteCalculator.getShortestRoute(station4, station5);
        System.out.println("Маршрут с двумя пересадками:");
        printRoute(route);
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 2.5 * (route.size() -1) + 2.0;
        assertEquals(expected, actual, DELTA);
    }

    @After
    public void tearDownRouteCalculatorTest() throws Exception {

    }

    /*===================================================================================*/
    private static RouteCalculator getRouteCalculator()
    {
        createStationIndex();
        return new RouteCalculator(testStationIndex);
    }

    private static void printRoute(List<Station> route)
    {
        Station previousStation = null;
        for(Station station : route)
        {
            if(previousStation != null)
            {
                Line prevLine = previousStation.getLine();
                Line nextLine = station.getLine();
                if(!prevLine.equals(nextLine))
                {
                    System.out.println("\tПереход на станцию " +
                            station.getName() + " (" + nextLine.getName() + " линия)");
                }
            }
            System.out.println("\t" + station.getName());
            previousStation = station;
        }
    }

    private static void createStationIndex()
    {
        testStationIndex = new StationIndex();
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseConnections(JSONArray connectionsArray)
    {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                int lineNumber = ((Long) itemObject.get("line")).intValue();
                String stationName = (String) itemObject.get("station");

                Station station = testStationIndex.getStation(stationName, lineNumber);
                if(station == null)
                {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            testStationIndex.addConnection(connectionStations);
        });
    }

    private static void parseStations(JSONObject stationsObject)
    {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            int lineNumber = Integer.parseInt((String) lineNumberObject);
            Line line = testStationIndex.getLine(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                testStationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray)
    {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((Long) lineJsonObject.get("number")).intValue(),
                    (String) lineJsonObject.get("name")
            );
            testStationIndex.addLine(line);
        });
    }

    private static String getJsonFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(dataFile));
            lines.forEach(line -> builder.append(line));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}