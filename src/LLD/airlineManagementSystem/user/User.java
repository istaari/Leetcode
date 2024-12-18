package LLD.airlineManagementSystem.user;

public abstract class User {

    private final String id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final UserType userType;

    public User(UserBuilder<?> userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.email = userBuilder.email;
        this.phoneNumber = userBuilder.phoneNumber;
        this.userType = userBuilder.userType;
    }


    public abstract static class UserBuilder<T extends UserBuilder<T>> {

        private String id;
        private String name;
        private String email;
        private String phoneNumber;
        private UserType userType;

        public T setId(String id) {
            this.id = id;
            return self();
        }

        public T setEmail(String email) {
            this.email = email;
            return self();
        }

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return self();
        }

        public T setUserType(UserType userType) {
            this.userType = userType;
            return self();
        }

        protected abstract T self();

        public abstract User build();
    }


}
