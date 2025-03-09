package backend.Item;

import backend.Vendor.Vendor;

public class ItemDTO {

    private String name;
    private String description;
    private Integer stockCount;
    private Long vendorId;

    public ItemDTO(String name, String description, Integer stockCount, Long vendorId) {
        this.name = name;
        this.description = description;
        this.stockCount = stockCount;
        this.vendorId = vendorId;
    }

    public boolean isValid() {
        return name != null
                && description != null
                && stockCount != null
                && vendorId != null
                && !name.isBlank()
                && !description.isBlank();

    }

    public Long getVendorId() {
        return vendorId;
    }

    public String getName() {
        return name;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public String getDescription() {
        return description;
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

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
