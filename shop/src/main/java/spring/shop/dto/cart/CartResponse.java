package spring.shop.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.shop.domain.Cart;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long no;
    private String name;
    private int count;

    public CartResponse(Cart cart) {
        this.no = cart.getNo();
        this.name = cart.getItem().getName();
        this.count = cart.getCount();
    }

}
