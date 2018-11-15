package workshop.provider.test;


import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("BusService") // Set up name of tested provider
//@PactFolder("../pacts") // Point where to find pacts (See also section Pacts source in documentation)
@PactBroker(host = "localhost", port = "8113")
public class BusServiceContractTest {

    // Method will be run before testing interactions that require "with-data" state
    @State("There is a bus with number 613 arriving at Hammersmith bus station")
    public void hammerSmith() {
        System.out.println("There is a bus with number 613 arriving to Hammersmith bus station");
    }

    // Annotation denotes Target that will be used for tests
    @TestTarget
    public final Target target = new HttpTarget(8115);

}
