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
    private Integer stockCount;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setId(Long id) {
        this.id = id;
    }
}