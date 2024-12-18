package LLD.airlineManagementSystem;

import LLD.airlineManagementSystem.seat.Seat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Flight {

    private final String flightID;
    private final String source;
    private final String destination;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final Map<Integer, Seat> seats;
    private Aircraft aircraft;

    // Constructor
    public Flight(String flightID, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seats = new HashMap<>();
    }

    public String getFlightID() {
        return flightID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getSeatNumber(), seat);
    }

    public Seat getSeat(int seatNumber) {
        return seats.get(seatNumber);
    }


}
