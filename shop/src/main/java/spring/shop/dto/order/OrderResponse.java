package spring.shop.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.shop.domain.Order;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long no;
    private String name;
    private LocalDateTime orderDate;
    private int count;

    private int orderPrice;

    public OrderResponse(Order order) {
        this.no = order.getNo();
        this.name = order.getItem().getName();
        this.orderDate = order.getOrderDate();
        this.count = order.getCount();
        this.orderPrice = order.getOrderPrice();
    }


}
