package backend.Vendor;

import backend.Registration.UserDTO;
import backend.User.Role;

public class VendorDTO extends UserDTO {

    private String businessName;
    private String businessAddress;


    public VendorDTO(String username, String password, String businessName, String businessAddress) {
        super(username, password, Role.VENDOR.getName());
        this.businessName = businessName;
        this.businessAddress = businessAddress;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}