package LLD.airlineManagementSystem.booking;

import LLD.airlineManagementSystem.BookingStatus;
import LLD.airlineManagementSystem.Flight;
import LLD.airlineManagementSystem.payment.Payment;
import LLD.airlineManagementSystem.seat.Seat;
import LLD.airlineManagementSystem.user.Passenger;


@SuppressWarnings("all")
public class Booking {

    private final String bookingNumber;
    private final Flight flight;
    private final Passenger passenger;
    private final Seat seat;
    private final double price;
    private final Payment payment;
    private BookingStatus status;

    public Booking(String bookingNumber, Flight flight, Passenger passenger, Seat seat, double price, Payment payment) {
        this.bookingNumber = bookingNumber;
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.price = price;
        this.status = BookingStatus.CONFIRMED;
        this.payment = payment;

    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

}
