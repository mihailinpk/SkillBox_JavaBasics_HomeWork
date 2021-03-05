import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class TreeWebPagesBuilder extends RecursiveTask<Node> {

    private String userAgent;
    private String refferer;
    private String currentUrl;
    private Set<String> uniqueUrlsSet = Collections.synchronizedSet(new HashSet<>());

    public TreeWebPagesBuilder(String userAgent, String refferer, String currentUrl) {
        this.userAgent = userAgent;
        this.refferer = refferer;
        this.currentUrl = currentUrl;
    }

    public TreeWebPagesBuilder(
                String userAgent, String refferer, String currentUrl, Set<String> uniqueUrlsSet
    ) {
        this.userAgent = userAgent;
        this.refferer = refferer;
        this.currentUrl = currentUrl;
        this.uniqueUrlsSet = uniqueUrlsSet;
    }

    @Override
    protected Node compute() {

        Node node = new Node(currentUrl, null);

        try {

            Thread.sleep(200);
            Document doc = Jsoup.connect(currentUrl).userAgent(userAgent).referrer(refferer).get();
            Set<String> linksFromCurrentPageSet = Collections.synchronizedSet(new LinkedHashSet<>());
            Elements elements = doc.select("a");
            for (Element element : elements)    {
                String href = element.attr("href");
                if (href.matches(currentUrl + "[^#]*") && !uniqueUrlsSet.contains(href))    {
                    linksFromCurrentPageSet.add(href);
                    uniqueUrlsSet.add(href);
                }
            }

            List<TreeWebPagesBuilder> subTasks = new LinkedList<>();
            Set<Node> subNodeSet = Collections.synchronizedSet(new LinkedHashSet<>());
            for(String url : linksFromCurrentPageSet)   {
                TreeWebPagesBuilder task = new TreeWebPagesBuilder(userAgent, refferer, url, uniqueUrlsSet);
                task.fork();
                subTasks.add(task);
            }

            for(TreeWebPagesBuilder task : subTasks)    {
                subNodeSet.add(task.join());
            }

            node.setChildren(subNodeSet);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return node;

    }

}