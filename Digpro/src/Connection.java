
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class describes the Http-connection.
 *
 * @author Lizhong Wang
 */
public class Connection {

    static String status;

    /**
     * This method first enabel the connection to the target address and then
     * fetch the data from web page. Note the target is encoded in ISO-8859-1.
     *
     * @return the data/Http response are returned as a list.
     */
    public static List<String[]> initialization() {

        String tagWebpage = "http://daily.digpro.se/bios/servlet/bios.servlets.web.RecruitmentTestServlet";

        List<String[]> dataList = new ArrayList<>();

        OutputGui out = new OutputGui(Gui.statusText);
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String thisTime = dateFormat.format(now);

        try {
            System.out.println();
            System.out.println(" ------ " + thisTime + " ------ ");

            status = "Sending HTTP Request to: " + tagWebpage;
            System.out.println(status);

            URL url = new URL(tagWebpage);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            status = "Waiting for server response ......";
            System.out.println(status);

            status = "Reciving HTTP Response: [Code: " + conn.getResponseCode() + "] [Message: " + conn.getResponseMessage() + "] ";
            System.out.println(status);

            status = "Loading the data from server ......";
            System.out.println(status);

            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "ISO-8859-1"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.charAt(0) != '#') {
                        String[] temp = line.split(", ");
                        dataList.add(temp);
                    }
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        status = "Loading successfully completed ";
        System.out.print(status);

        return dataList;
    }

}
