package LLD.airlineManagementSystem.seat;

public class Seat {
    private final int seatNumber;
    private final SeatType seatType;
    private SeatStatus seatStatus;

    public Seat(int seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public void reserve() {
        this.seatStatus = SeatStatus.RESERVED;
    }

    public void release() {
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public void occupy() {
        this.seatStatus = SeatStatus.OCCUPIED;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
