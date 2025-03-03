package backend.Vendor;

import backend.User.Role;
import backend.User.UserRepository;
import backend.User.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService implements VendorServiceInterface {

    private final UserService userService;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    public VendorService(UserService userService, UserRepository userRepository, VendorRepository vendorRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public boolean validate(VendorDTO vendor) {
        boolean isValid = vendor != null &&

                vendor.getBusinessName() != null &&
                vendor.getBusinessAddress() != null &&

                !vendor.getBusinessName().isBlank() &&
                !vendor.getBusinessAddress().isBlank()
                ;
        return userService.validate(vendor) && isValid &&
                vendor.getRole().equals(Role.VENDOR.getName());
    }

    @Override
    public boolean available(VendorDTO vendor) {
        return
                userRepository.findByEmail(vendor.getUsername()).isEmpty() &&
                vendorRepository.findByBusinessName(vendor.getBusinessName()).isEmpty();
    }

    @Override
    public boolean register(VendorDTO vendor) {
        Vendor actualVendor = new Vendor();
        actualVendor.setEmail(vendor.getUsername());
        actualVendor.setPassword(vendor.getPassword());
        actualVendor.setRole(Role.VENDOR);
        actualVendor.setBusinessName(vendor.getBusinessName());
        actualVendor.setBusinessAddress(vendor.getBusinessAddress());
        vendorRepository.save(actualVendor);
        return true;
    }

    @Override
    public boolean update(Long id, VendorDTO vendor) {
        assert id != null;
        Vendor actualVendor = new Vendor(id, vendor.getUsername(), vendor.getPassword(), vendor.getBusinessName(), vendor.getBusinessAddress());
        actualVendor.setId(id);
        actualVendor.setEmail(vendor.getUsername());
        actualVendor.setPassword(vendor.getPassword());
        actualVendor.setBusinessName(vendor.getBusinessName());
        actualVendor.setBusinessAddress(vendor.getBusinessAddress());
        vendorRepository.save(actualVendor);
        return true;
    }

    @Override
    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findVendorById(id);
    }

    @Override
    public boolean delete(Long id) {
        return vendorRepository.findVendorById(id).map(vendor -> {
            vendor.setIsDeleted(true);
            vendorRepository.save(vendor);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<Vendor> getVendorByUsername(String username) {
        return vendorRepository.findVendorByEmail(username);
    }
}
