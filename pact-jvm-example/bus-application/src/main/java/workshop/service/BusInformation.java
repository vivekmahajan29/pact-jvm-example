package workshop.service;

public class BusInformation {

    String station;
    String number;
    Integer estimatedArrivalTime;


    public BusInformation(String station, String nr, Integer eta) {
        this.station = station;
        this.number = nr;
        this.estimatedArrivalTime = eta;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(Integer estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
