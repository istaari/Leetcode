package LLD.airlineManagementSystem.user;

public class Staff extends User {

    public Staff(StaffBuilder builder) {
        super(builder);
    }

    public static class StaffBuilder extends UserBuilder<StaffBuilder> {

        @Override
        protected StaffBuilder self() {
            return this;
        }

        @Override
        public User build() {
            return new Staff(this);
        }

    }

}
