package backend.Vendor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VendorServiceInterface {
    /**
     * @param vendor the vendor to check
     * this method returns true if the vendor's details provided are valid
     * */
    boolean validate(VendorDTO vendor);
    /**
     * @param vendor the vendor to check
     * this method returns true if the vendor's vendor's username and business name is unique and not already in the database
     * */
    boolean available(VendorDTO vendor);

    /**
     * @param vendor the vendor that needs to be registered
     *
     * */
    boolean register(VendorDTO vendor);

    /**
     * @param id the id of the vendor that needs to be updated
     * @param vendor the new vendor details
     *
     * */
    boolean update(Long id, VendorDTO vendor);

    /**
     * @param id the id of the vendor to return
     */
    Optional<Vendor> getVendorById(Long id);

    /**
     * @param id the id of the vendor to delete
     * */
    boolean delete(Long id);

    /**
     * @param username the username of the vendor to return
     * */
    Optional<Vendor> getVendorByUsername(String username);
}
