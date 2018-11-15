package workshop.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BusController {

    @RequestMapping("/bus/{station}/{number}")
    public BusInformation bus(@PathVariable String station, @PathVariable String number) {
        int estimatedArrivalTime = getEstimatedArrivalTime();
        BusInformation busInformation = new BusInformation(station, number, estimatedArrivalTime);
        return busInformation;
    }

    private int getEstimatedArrivalTime() {
        Random rn = new Random();
        int min = 1;
        int max = 7;
        return rn.nextInt(max - min + 1) + min;
    }
}
