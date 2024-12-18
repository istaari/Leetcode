package LLD.airlineManagementSystem;


@SuppressWarnings("all")
public class Aircraft {

    private final String aircraftID;
    private final String model;
    private final int capacity;
    private final String manufacturer;
    private final double fuelCapacity;

    // Constructor
    public Aircraft(String aircraftID, String model, int capacity, String manufacturer, double fuelCapacity) {
        this.aircraftID = aircraftID;
        this.model = model;
        this.capacity = capacity;
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
    }

}
