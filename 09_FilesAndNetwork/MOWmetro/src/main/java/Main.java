import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private final static String PAGE_ADDRESS = "https://www.moscowmap.ru/metro.html#lines";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36";
    private final static String REFFERER = "http://www.google.com";
    private final static String JSON_FILE_NAME = "src/main/resources/map.json";
    private final static Metro moscowMetro = new Metro();

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(PAGE_ADDRESS).userAgent(USER_AGENT)
                    .referrer(REFFERER).maxBodySize(0).get();
            System.out.println("===================================================");
            System.out.println(doc.title());
            System.out.println("===================================================");
            writeStationsToMetro(doc);
            writeConnectionsToMetro(doc);
            writeLinesToMetro(doc);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(JSON_FILE_NAME), moscowMetro);
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        System.out.print("Программа завершила работу...");
    }

    private static void writeStationsToMetro(Document doc) {
        Map<String, List<String>> stations = new LinkedHashMap<>();
        Elements elements = doc.select("div.js-metro-stations");
        for(Element element : elements) {
            String lineNumber = element.attr("data-line");
            Elements elementsStationsNames = element.select("span.name");
            List<String> stationsList = new ArrayList<>();
            for (Element stationNameElement : elementsStationsNames)    {
                String stationName = stationNameElement.text();
                stationsList.add(stationName);
            }
            stations.put(lineNumber, stationsList);
        }
        moscowMetro.setStations(stations);
    }

    private static void writeConnectionsToMetro(Document doc)   {
        List<List<Connection>> connections = new ArrayList<>();
        Elements elements = doc.select("div.js-metro-stations");
        for (Element element : elements)    {
            String stationLineFrom = element.attr("data-line");
            Elements stationElements = element.select("a");
            for (Element stationElement : stationElements)  {
                String stationNameFrom = stationElement.select("span.name").text();
                Elements elementsConnectStations = null;
                try {
                    elementsConnectStations = stationElement.select("span.t-icon-metroln");
                } catch (Exception ex)  {}
                if (elementsConnectStations.size() != 0) {
                    Connection connectionFrom = new Connection(stationLineFrom, stationNameFrom);
                    List<Connection> connectionNestedArray = new ArrayList<>();
                    connectionNestedArray.add(connectionFrom);
                    for (Element elementConnect : elementsConnectStations)  {
                        String stationLineToTag = elementConnect.selectFirst("span.t-icon-metroln").toString();
                        String stationLineTo = stationLineToTag.substring(31, stationLineToTag.lastIndexOf(" title=")-1);
                        String stationNameTo = stationLineToTag.substring(
                                                    stationLineToTag.indexOf("«") + 1, stationLineToTag.lastIndexOf("»"));
                        Connection connectionTo = new Connection(stationLineTo, stationNameTo);
                        connectionNestedArray.add(connectionTo);
                    }
                    connections.add(connectionNestedArray);
                }
            }
        }
        moscowMetro.setConnections(connections);
    }

    private static void writeLinesToMetro(Document doc) throws IOException {
        Elements elements = doc.select("span.js-metro-line");
        List<Line> lines = new ArrayList<>();
        for (Element element : elements)    {
            Line line = new Line(element.attr("data-line"), element.text());
            lines.add(line);
        }
        moscowMetro.setLines(lines);
    }

}