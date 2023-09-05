package own.micro.products;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class Parser {

    public void parseItems(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        var elementsDiv = document.select("div.common__recommendations__list");
//        var element = elementsDiv.select("div.common__recommendations__list__item one-five");
        var elementT = elementsDiv.select("div.common__recommendations__list__item one-five");

        for (Element e: elementT) {
            System.out.println(e);
        }

//        System.out.println(elementsDiv);


    }

}
