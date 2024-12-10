package designPatterns;



/*
 * Facade Design Pattern
 *
 * Definition:
 * The Facade Pattern provides a unified, simpler interface to a complex subsystem. It acts as a "front door"
 * that clients can use to interact with a complicated set of classes, methods, or processes.
 *
 * Real-World Explanation:
 * Imagine you’re booking a vacation package:
 *
 * You could interact individually with:
 * - Airlines for flight bookings.
 * - Hotels for reservations.
 * - Travel agencies for sightseeing tours.
 *
 * But instead, you use a travel agent. They coordinate everything behind the scenes and give you a single interface
 * (a package with flights, hotels, and tours). This travel agent acts as a Facade for the travel industry.
 */

// Subsystems remain unchanged
class FlightBooking {
    public void bookFlight(String destination) {
        System.out.println("Flight booked to " + destination);
    }
}

class HotelBooking {
    public void bookHotel(String destination) {
        System.out.println("Hotel booked in " + destination);
    }
}

class TourBooking {
    public void bookTour(String destination) {
        System.out.println("Tour booked in " + destination);
    }
}

// Facade
class TravelFacade {
    private final FlightBooking flight;
    private final HotelBooking hotel;
    private final TourBooking tour;

    public TravelFacade() {
        this.flight = new FlightBooking();
        this.hotel = new HotelBooking();
        this.tour = new TourBooking();
    }

    public void bookCompleteTrip(String destination) {
        System.out.println("Booking a complete trip to " + destination + "...");
        flight.bookFlight(destination);
        hotel.bookHotel(destination);
        tour.bookTour(destination);
    }
}

// Usage
public class Facade {
    public static void main(String[] args) {
        TravelFacade travel = new TravelFacade();
        // Client interacts with a single interface
        travel.bookCompleteTrip("Paris");
    }
}


