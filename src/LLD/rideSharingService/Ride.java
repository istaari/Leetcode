package LLD.rideSharingService;

import java.util.UUID;

public class Ride {

    private final String rideId; // Mandatory
    private final Passenger passenger; // Mandatory
    private final Location source; // Mandatory
    private final Location destination; // Mandatory
    private  Driver driver; // Mandatory
    private Location currentLocation; // Mandatory
    private RideStatus status; // Mandatory
    private double fare; // Optional

    public Ride(Passenger passenger, Location source, Location destination, Location currentLocation, double fare) {
        this.rideId = UUID.randomUUID().toString();
        this.passenger = passenger;
        this.source = source;
        this.destination = destination;
        this.currentLocation = currentLocation;
        this.status = RideStatus.REQUESTED;
        this.fare = fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", passenger=" + passenger +
                ", source=" + source +
                ", destination=" + destination +
                ", driver=" + driver +
                ", currentLocation=" + currentLocation +
                ", status=" + status +
                ", fare=" + fare +
                '}';
    }
}
