package backend.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    public Optional<Vendor> findByBusinessName(String businessName);

    Optional<Vendor> findVendorById(Long id);

    Optional<Vendor> findVendorByEmail(String email);
}
