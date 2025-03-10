package backend.Item;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ItemServiceInterface {
    Item create(ItemDTO item);

    Optional<Item> findById(Long id);

    Item update(ItemDTO item, Long id);

    void delete(Long id);
}
