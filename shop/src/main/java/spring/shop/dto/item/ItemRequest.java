package spring.shop.dto.item;

import lombok.Data;

@Data
public class ItemRequest {

    private String name;
    private int price;
    private int quantity;

}
