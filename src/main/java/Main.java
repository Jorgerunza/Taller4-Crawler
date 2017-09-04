/**
 * Created by Jorge on 2/09/2017.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        href();
        categories();


    }


    public static void href () throws IOException {
        Document doc = Jsoup.connect("https://play.google.com/store/apps/category/FINANCE/collection/topselling_paid").timeout(0).get();

        HashSet<String> hrefs = new HashSet<String>();

        Elements anchors = doc.getElementsByClass("card-click-target");

        for (Element element : anchors){
            hrefs.add ( "https://play.google.com/" + element.attr("href").toString());
            System.out.println( "https://play.google.com/" + element.attr("href").toString());
        }
    }

    public static void categories () throws IOException {
        Document doc = Jsoup.connect("https://play.google.com/store/apps/category/FINANCE/collection/topselling_paid").timeout(0).get();

        Document detailDoc =null;

        HashSet<String> hrefs = new HashSet<String>();
        String href = null;
        Elements anchors = doc.getElementsByClass("card-click-target");

        String description = null;

        for (Element element : anchors){
            href = "https://play.google.com/" + element.attr("href").toString();
            hrefs.add (href );
            System.out.println(href);
        }

        for (String url : hrefs){
            detailDoc = Jsoup.connect(url).timeout(0).get();
            description  = detailDoc.select ("[itemprop='description']").text();
            System.out.println(description);
        }
    }




}
