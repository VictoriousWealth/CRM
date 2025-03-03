package backend.Vendor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return vendorService.getVendorByUsername(username).map(vendor -> ResponseEntity.ok("Vendor is <" + vendor.toString() + ">."))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody VendorDTO vendor) {
        boolean isValidated = vendorService.validate(vendor);
        boolean valid = isValidated && vendorService.available(vendor);

        if (valid && vendorService.update(id, vendor)) {
            return ResponseEntity.ok("Vendor <"+ vendor.getUsername() +"> updated successfully");
        }

        return ResponseEntity.badRequest().body(isValidated ? "Username or business name is already taken!" : "Invalid details supplied!");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorService.delete(id);
        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
