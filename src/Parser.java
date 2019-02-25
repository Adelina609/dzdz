import java.io.File;
import java.io.IOException;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class Parser {

    public static void main(String[] args) {
        String path = "C:\\Users\\user\\Downloads\\Telegram Desktop\\Parser\\src\\files\\main_page_nohtml.html";
        PagesSaver saver = new PagesSaver();
        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode html = cleaner.clean(new File(path));
            Object[] tags = html.evaluateXPath(
                    "//div[@class='container']/ul[@class='collection-menu-vertical list']" +
                    "/li[@class='list-item menu-item   collapse']/a[@class='menu-link']");
            for (Object tag : tags) {
                TagNode aTag = (TagNode) tag;
                System.out.println(aTag.getAttributeByName("href")+  " " + aTag.getText().toString());
                saver.savePage(aTag.getAttributeByName("href"));
                getProductList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getProductList() throws IOException, XPatherException {
        HtmlCleaner htmlcleaner = new HtmlCleaner();
        TagNode htmlpage = htmlcleaner.clean(new File("C:\\Users\\user\\Downloads\\Telegram Desktop\\Parser\\src\\files\\current_page.html"));
        Object[] products = htmlpage.evaluateXPath(
                "//div[@class='products-list row']/div[@class='product-card cell-xl-4 cell-md-6 cell-xs-12']");
        int size  = products.length;

        for (int i  = 0; i < size; i++) {
            //image
            String li = htmlpage.evaluateXPath("//div[@class='products-list row']/div[@class='product-card cell-xl-4 cell-md-6 cell-xs-12']/" +
                    "div[@class='card-inner']/div[@class='product-photo']/a/img/@src")[i].toString();
            System.out.println(li);
            //title
            li = htmlpage.evaluateXPath("//div[@class='products-list row']/div[@class='product-card cell-xl-4 cell-md-6 cell-xs-12']/" +
                    "div[@class='card-inner']/div[@class='product-photo']/a/@title")[i].toString();
            System.out.println(li);
            //link
            li = "https://geek-trip.ru" + htmlpage.evaluateXPath("//div[@class='products-list row']/div[@class='product-card cell-xl-4 cell-md-6 cell-xs-12']/" +
                    "div[@class='card-inner']/div[@class='product-photo']/a/@href")[i].toString();
            System.out.println(li);
            //price
            li = htmlpage.evaluateXPath("//div[@class='products-list row']/div[@class='product-card cell-xl-4 cell-md-6 cell-xs-12']/" +
                    "div[@class='card-inner']/div[@class='product-caption']" +
                    "/div[@class='product-prices']/span/text()")[i].toString();
            System.out.println(li);

        }

    }

}