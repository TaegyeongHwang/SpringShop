package spring.shop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.shop.domain.Item;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long no;
    private String name;
    private int price;
    private int quantity;

    public ItemResponse(Item item) {
        this.no = item.getNo();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
    }

}
