package spring.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.shop.domain.Item;
import spring.shop.dto.item.ItemRequest;
import spring.shop.dto.item.ItemResponse;
import spring.shop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Item Register
     */
    @Transactional
    public ItemResponse register(ItemRequest itemRequest) {

        Optional<Item> findItem = itemRepository.findItemByName(itemRequest.getName());
        if (findItem.isEmpty()) {
            Item item = Item.builder()
                    .name(itemRequest.getName())
                    .price(itemRequest.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .build();

            itemRepository.save(item);

            Item saveItem = itemRepository.findItem(item.getNo());

            ItemResponse response = ItemResponse.builder()
                    .no(saveItem.getNo())
                    .name(saveItem.getName())
                    .price(saveItem.getPrice())
                    .quantity(saveItem.getQuantity())
                    .build();

            return response;
        } else {

            throw new RuntimeException("This item is already registered");
        }
    }

    /**
     * Item FindList
     */
    public List<ItemResponse> findList() {

        List<Item> items = itemRepository.findAll();

        List<ItemResponse> response = items.stream()
                .map(ItemResponse::new)
                .collect(Collectors.toList());

        return response;
    }

    /**
     * Item Search
     */
    public List<ItemResponse> search(String itemName) {

        Optional<Item> findItem = itemRepository.findItemByName(itemName);
        if (findItem.isEmpty()) {

            throw new RuntimeException("There is no item");
        }

        List<ItemResponse> response = findItem.stream()
                .map(ItemResponse::new)
                .collect(Collectors.toList());

        return response;
    }


}
