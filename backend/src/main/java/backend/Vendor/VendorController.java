package backend.Vendor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    public static final String USERNAME = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello " + USERNAME + "!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVendor(@PathVariable Long id) {
        Optional<Vendor> isVendorPresent = vendorService.getVendorById(id);
        return isVendorPresent.map(vendor -> ResponseEntity.ok("Vendor is <" + vendor + ">."))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody VendorDTO vendor) {
        boolean isValidated = vendorService.validate(vendor);
        boolean valid = isValidated && vendorService.available(vendor);

        if (valid && vendorService.update(id, vendor)) {
            return ResponseEntity.ok("Vendor <"+ vendor.getUsername() +"> updated successfully");
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username or business name is already taken!" : "Invalid username or business name or password!");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorService.delete(id);
        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
