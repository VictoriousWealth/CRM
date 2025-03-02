package backend.Item;

import backend.User.User;
import backend.Vendor.Vendor;
import jakarta.persistence.*;

@Entity
@Table(name = "CRM_item")
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String stockCount;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

}