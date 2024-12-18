package LLD.airlineManagementSystem.user;

public class Admin extends User {

    public Admin(AdminBuilder builder) {
        super(builder);
    }

    public static class AdminBuilder extends UserBuilder<AdminBuilder> {

        @Override
        protected AdminBuilder self() {
            return this;
        }

        @Override
        public User build() {
            return new Admin(this);
        }

    }


}