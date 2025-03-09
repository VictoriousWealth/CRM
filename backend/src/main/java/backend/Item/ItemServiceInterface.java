package backend.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemServiceInterface {
    boolean create(ItemDTO item);
}
