import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PagesSaver {

    public void savePage (String str) throws IOException {
        URL url = new URL(str);
        BufferedReader reader  = new BufferedReader(
                new InputStreamReader(url.openConnection().getInputStream(), "UTF-8")
        );
        FileWriter writer = new FileWriter("C:\\Users\\user\\Downloads\\Telegram Desktop\\Parser\\src\\files\\current_page.html");
        while (true) {
            String string = reader.readLine();
            if (string ==  null) {
                break;
            }
            writer.write(string + '\n');
        }
        writer.close();
    }
}
