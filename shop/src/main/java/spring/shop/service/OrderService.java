package spring.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.shop.domain.Cart;
import spring.shop.domain.Item;
import spring.shop.domain.Order;
import spring.shop.dto.order.OrderResponse;
import spring.shop.repository.CartRepository;
import spring.shop.repository.ItemRepository;
import spring.shop.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    /**
     * Order Register
     */
    @Transactional
    public void register(List<Integer> cart) {

        for (int i = 0; i < cart.size(); i++) {
            Long cartNo = Long.valueOf(cart.get(i));
            Cart findCart = cartRepository.findCart(cartNo);
            if (findCart == null) {
                break;
            }
            Item findItem = itemRepository.findItem(findCart.getItem().getNo());

            if (findItem.getQuantity() - findCart.getCount() < 0) {

                throw new RuntimeException("Item stock is insufficient");
            }

            Order order = Order.builder()
                    .orderDate(LocalDateTime.now())
                    .count(findCart.getCount())
                    .item(findItem)
                    .orderPrice(findCart.getCount() * findItem.getPrice())
                    .build();

            findItem.setQuantity(findItem.getQuantity() - findCart.getCount());

            orderRepository.save(order);
            cartRepository.remove(findCart);
        }
    }

    /**
     * Order FindList
     */
    public List<OrderResponse> findList() {

        List<Order> orders = orderRepository.findAll();

        List<OrderResponse> response = orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());

        return response;
    }
}
