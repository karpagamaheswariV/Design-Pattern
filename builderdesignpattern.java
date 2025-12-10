public class Main {
    public static void main(String[] args) {

        User user1 = User.builder()
                .setName("Sameer")
                .setEmail("abc@gmail.com")
                .setPhone("9876543210")
                .setAddress("Chennai")
                .build();

        System.out.println("User Created: " + user1);
    }
}

class User {
    String name;
    String email;
    String phoneNo;
    String address;

    private User(String name, String email, String phoneNo, String address) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    static class UserBuilder {
        String name;
        String email;
        String phoneNo;
        String address;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhone(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(name, email, phoneNo, address);
        }
    }

    public String toString() {
        return name + " | " + address + " | " + email + " | " + phoneNo;
    }
}
