import java.util.List;
import java.util.Map;

public class Metro {
    private Map<String, List<String>> stations;
    private List<List<Connection>> connections;
    private List<Line> lines;

    public Map<String, List<String>> getStations() {
        return stations;
    }

    public void setStations(Map<String, List<String>> stations) {
        this.stations = stations;
    }

    public List<List<Connection>> getConnections() {
        return connections;
    }

    public void setConnections(List<List<Connection>> connections) {
        this.connections = connections;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
