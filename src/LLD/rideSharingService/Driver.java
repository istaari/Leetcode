package LLD.rideSharingService;

public class Driver extends User {

    private final String licensePlate;
    private DriverStatus driverStatus;

    public Driver(String userid, String name, String contact, Location location, String licensePlate) {
        super(userid, name, contact, location);
        this.licensePlate = licensePlate;
        this.driverStatus = DriverStatus.AVAILABLE;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

}
