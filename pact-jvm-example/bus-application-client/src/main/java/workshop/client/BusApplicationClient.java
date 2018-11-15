package workshop.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class BusApplicationClient {

    private int port = 8115;

    BusApplicationClient() {
        // Will use default port.
        System.out.println("Default port " + port);
    }

    BusApplicationClient(int port) {
        this.port = port;
        System.out.println("Custom port " + port);
    }

    public static void main(String[] args) throws IOException {
        Integer eta = new BusApplicationClient().getEstimatedArrivalTime("Hammersmith", "613");
        System.out.println("estimated arrival time: " + eta);
    }

    public Integer getEstimatedArrivalTime(String station, String number) throws IOException {

        String url = String.format("http://localhost:%d/bus/%s/%s", port, station, number);
        System.out.println("using url: " + url);

        HttpResponse response = Request.Get(url).execute().returnResponse();
        String json = EntityUtils.toString(response.getEntity());
        System.out.println("json=" + json);

        JSONObject jsonObject = new JSONObject(json);
        String estimatedArrivalTime = jsonObject.get("estimatedArrivalTime").toString();
        return new Integer(estimatedArrivalTime);

    }
}
