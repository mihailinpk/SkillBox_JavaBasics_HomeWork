import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Main {

    private final static String MAIN_PAGE_ADDRESS = "https://lenta.ru/";
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36";
    private final static String REFFERER = "http://www.google.com";
    private final static String FOLDER_FOR_SAVE_IMAGES = "images";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(MAIN_PAGE_ADDRESS).userAgent(USER_AGENT).referrer(REFFERER).get();
            for (Element element : doc.select("img[src~=([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)]"))  {
                String linkImage = element.attr("abs:src");
                String saveFileName = linkImage.substring(linkImage.lastIndexOf('/') + 1);
                downloadFile(linkImage, saveFileName);
            }
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
        System.out.print("Программа завершила работу...");
    }

    private static void downloadFile(String linkImage, String saveFileName) throws IOException {
        URL url = new URL(linkImage);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(FOLDER_FOR_SAVE_IMAGES + "/" + saveFileName);
        byte[] buffer = new byte[1024];
        int count = 0;
        while((count = bis.read(buffer,0,1024)) != -1)  {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        System.out.printf("%s скачан\n", saveFileName);
    }
}