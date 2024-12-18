package LLD.airlineManagementSystem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearch {
    private final List<Flight> flights;

    public FlightSearch(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> search(String source, String destination, LocalDateTime dateTime) {
        return flights.stream().filter((flight) -> flight.getSource().equals(source) &&
                flight.getDestination().equals(destination) &&
                flight.getDepartureTime().equals(dateTime)).collect(Collectors.toList());

    }
}
