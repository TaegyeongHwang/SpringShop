package spring.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.shop.domain.Cart;
import spring.shop.domain.Item;
import spring.shop.dto.cart.CartRequest;
import spring.shop.dto.cart.CartResponse;
import spring.shop.repository.CartRepository;
import spring.shop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    /**
     * Cart Add
     */
    @Transactional
    public CartResponse add(CartRequest cartRequest) {

        Item item = itemRepository.findItem(cartRequest.getNo());
        if (item == null) {

            throw new RuntimeException("There is no item");
        }

        Optional<Cart> findCart = cartRepository.findCartByName(item.getName());
        if (findCart.isEmpty()) {

            Cart cart = Cart.builder()
                    .count(cartRequest.getCount())
                    .item(item)
                    .build();

            cartRepository.save(cart);

            Cart saveCart = cartRepository.findCart(cart.getNo());

            CartResponse response = CartResponse.builder()
                    .no(saveCart.getNo())
                    .name(saveCart.getItem().getName())
                    .count(saveCart.getCount())
                    .build();

            return response;
        } else {

            throw new RuntimeException("This item is already exist");
        }
    }

    /**
     * Cart Search
     */
    public List<CartResponse> search(String itemName) {

        Optional<Cart> findCart = cartRepository.findCartByName(itemName);
        if (findCart.isEmpty()) {

            throw new RuntimeException("There is no cart");
        }

        List<CartResponse> response = findCart.stream()
                .map(CartResponse::new)
                .collect(Collectors.toList());

        return response;
    }

    /**
     * Cart Delete
     */
    @Transactional
    public CartResponse delete(Long no) {

        Cart findCart = cartRepository.findCart(no);
        if (findCart == null) {

            throw new RuntimeException("There is no cart");
        }

        cartRepository.remove(findCart);

        CartResponse response = CartResponse.builder()
                .build();

        return response;
    }

    /**
     * Cart Delete
     */
    @Transactional
    public CartResponse deleteAll() {

        List<Cart> carts = cartRepository.findAll();

        for (int i = 0; i < carts.size(); i++) {
            cartRepository.remove(carts.get(i));
        }

        CartResponse response = CartResponse.builder()
                .build();

        return response;
    }
}
