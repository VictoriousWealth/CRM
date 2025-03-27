package backend.Item;

import backend.Vendor.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService implements ItemServiceInterface {

    private final VendorService vendorService;
    private final ItemRepository itemRepository;

    public ItemService(VendorService vendorService, ItemRepository itemRepository) {
        this.vendorService = vendorService;
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(ItemDTO item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setStockCount(item.getStockCount());
        newItem.setVendor(vendorService.getVendorById(item.getVendorId()).orElse(null));
        if (newItem.getVendor() == null) {
            return null;
        }
        List<Item> list = itemRepository.findByVendor_Id(newItem.getVendor().getId());
        for (Item i : list) {
            if (i.getName().equals(newItem.getName())) {
                if (i.getDescription().equals(newItem.getDescription())) {
                    if (Objects.equals(i.getStockCount(), newItem.getStockCount())) {
                        return null;
                    }
                }
            }
        }
        itemRepository.save(newItem);
        return newItem;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item update(ItemDTO itemDTO, Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) return null;

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setStockCount(itemDTO.getStockCount());
        item.setVendor(vendorService.getVendorById(itemDTO.getVendorId()).orElse(null));

        if (item.getVendor() == null) return null;

        List<Item> list = itemRepository.findByVendor_Id(item.getVendor().getId());
        if (list.contains(item)) {
            System.out.printf("Item %s is already in the list\n", item.getName());
            System.out.printf("Number of items in the list: %d\n", list.size());
            System.out.printf("List items: %s\n", list.toString());
            return null;
        }

        itemRepository.save(item);
        return item;
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }


}
