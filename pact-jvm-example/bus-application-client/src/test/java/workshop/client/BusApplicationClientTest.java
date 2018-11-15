package workshop.client;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class BusApplicationClientTest {
    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("BusService", "localhost", 8112, this);

    @Pact(consumer = "BusServiceClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");

        DslPart estimatedTimeOfArrivalResults = new PactDslJsonBody()
                .stringType("station", "Hammersmith")
                .stringType("number", "613")
                .integerType("estimatedArrivalTime", 4)
                .asBody();

        return builder
                .given("There is a bus with number 613 arriving at Hammersmith bus station")
                .uponReceiving("A request for estimated time of arrival for bus number 613 to Hammersmith bus station")
                .path("/bus/Hammersmith/613")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(estimatedTimeOfArrivalResults)
                .toPact();

    }

    @Before
    public void setup() {
        // Change output dir for generated pact-files
        System.setProperty("pact.rootDir", "../pacts");
    }

    @Test
    @PactVerification()
    public void testEstimatedArrivalTime() throws IOException {
        Integer estimatedTimeOfArrival = new BusApplicationClient(provider.getPort())
                .getEstimatedArrivalTime("Hammersmith", "613");
        System.out.println("According to test, estimated time of arrival is: " + estimatedTimeOfArrival);
        assertTrue(estimatedTimeOfArrival >= 0);
    }

}

