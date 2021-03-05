import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private final static String ROOT_URL = "http://www.fg-doors.ru/";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36";
    private final static String REFFERER = "http://www.google.com";
    private final static String FILE_NAME = "mapofsite.file";

    public static void main(String[] args) {

        Node rootNode = new ForkJoinPool().invoke(
                new TreeWebPagesBuilder(USER_AGENT, REFFERER, ROOT_URL)
        );

        try {

            Path path = Files.createFile(Paths.get(FILE_NAME));
            writeNodeToFile(rootNode, 0, path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nПрограмма завершила работу...");

    }

    private static void writeNodeToFile(Node node, int depth, Path path) throws IOException {

        String urlToWrite = "";
        for(int i=0; i<depth; i++)    {
            urlToWrite = urlToWrite + "\t";
        }

        Files.writeString(path, urlToWrite + node.getUrl() + "\n", StandardOpenOption.APPEND);
        
        for (Node nestedNode : node.getChildren())  {
            writeNodeToFile(nestedNode, depth + 1, path);
        }

    }

}