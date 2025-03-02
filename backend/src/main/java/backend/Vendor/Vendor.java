package backend.Vendor;

import backend.User.Role;
import backend.User.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crm_vendor")
public class Vendor extends User {

    @Column(nullable = false, unique = true)
    private String businessName;

    @Column(nullable = false)
    private String businessAddress;

    public Vendor() {}

    public Vendor(String username, String email, String businessName, String businessAddress) {
        super(username, email, Role.VENDOR);
        this.businessName = businessName;
        this.businessAddress = businessAddress;
    }

    // Getters and Setters
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessAddress() { return businessAddress; }
    public void setBusinessAddress(String businessAddress) { this.businessAddress = businessAddress; }
}
