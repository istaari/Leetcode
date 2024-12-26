package LLD.rideSharingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RideService {

    private final ConcurrentHashMap<String, User> driver;
    private final ConcurrentHashMap<String, User> passenger;
    private final List<Ride> rideRequests; // Pending ride requests
    private final Map<String, Ride> ongoingRides; // Ongoing rides mapped by driver ID


    public RideService() {
        this.driver = new ConcurrentHashMap<>();
        this.passenger = new ConcurrentHashMap<>();
        this.rideRequests = new ArrayList<>();
        this.ongoingRides = new ConcurrentHashMap<>();
    }

    public void addDriver(User user) {
        driver.put(user.getUserid(), user);
    }

    public User getDriver(String userId) {
        return driver.get(userId);
    }

    public void addPassenger(User user) {
        passenger.put(user.getUserid(), user);
    }

    public User getPassenger(String userId) {
        return passenger.get(userId);
    }


}
