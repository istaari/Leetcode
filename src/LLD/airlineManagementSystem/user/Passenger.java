package LLD.airlineManagementSystem.user;

public class Passenger extends User {

    public Passenger(PassengerBuilder builder) {
        super(builder);
    }

    public static class PassengerBuilder extends UserBuilder<PassengerBuilder> {

        @Override
        protected PassengerBuilder self() {
            return this;
        }

        @Override
        public User build() {
            return new Passenger(this);
        }

    }

}
