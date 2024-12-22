package LLD.airlineManagementSystem.booking;

import LLD.airlineManagementSystem.BookingStatus;
import LLD.airlineManagementSystem.Flight;
import LLD.airlineManagementSystem.payment.Payment;
import LLD.airlineManagementSystem.payment.PaymentMethod;
import LLD.airlineManagementSystem.payment.PaymentProcessor;
import LLD.airlineManagementSystem.seat.Seat;
import LLD.airlineManagementSystem.user.Passenger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookingManager {
    Map<String, Booking> bookingMap;

    PaymentProcessor paymentProcessor;

    public BookingManager() {
        this.bookingMap = new HashMap<>();
    }

    public void setPaymentStrategy(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public Booking book(Flight flight, Passenger passenger, Seat seat, double price, PaymentMethod paymentMethod) {
        Payment payment = paymentProcessor.processPayment(BigDecimal.valueOf(price), paymentMethod);
        String bookingNumber = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingNumber, flight, passenger, seat, price, payment);
        bookingMap.put(bookingNumber, booking);
        return booking;
    }

    public void cancel(String bookingNumber) {
        Booking booking = bookingMap.get(bookingNumber);
        if (booking != null && booking.getStatus() == BookingStatus.CONFIRMED) {
            paymentProcessor.refund(booking.getPayment());
            booking.setStatus(BookingStatus.CANCELLED);
        }
    }


}
