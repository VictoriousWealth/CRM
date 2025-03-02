package backend.User;

public enum Role {
    CUSTOMER, VENDOR, ADMIN;


    public String getName() {
        return this.name().toUpperCase();
    }
}