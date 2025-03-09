package backend.Item;

import backend.Vendor.VendorService;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements ItemServiceInterface {

    private final VendorService vendorService;
    private final ItemRepository itemRepository;

    public ItemService(VendorService vendorService, ItemRepository itemRepository) {
        this.vendorService = vendorService;
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean create(ItemDTO item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setStockCount(item.getStockCount());
        newItem.setVendor(vendorService.getVendorById(item.getVendorId()).orElse(null));
        if (newItem.getVendor() == null) {
            return false;
        }

        itemRepository.save(newItem);
        return true;
    }
}
