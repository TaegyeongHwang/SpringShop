package spring.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.shop.dto.ResponseDTO;
import spring.shop.dto.cart.CartDeleteRequest;
import spring.shop.dto.cart.CartRequest;
import spring.shop.dto.cart.CartResponse;
import spring.shop.service.CartService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    /**
     * Cart Add
     */
    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody CartRequest cartRequest) {
        log.info("addCart()");

        try {
            CartResponse response = cartService.add(cartRequest);

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            ResponseDTO response = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    /**
     * Cart Search
     */
    @GetMapping("/search/{itemName}")
    public ResponseEntity<?> searchCart(@PathVariable String itemName) {
        log.info("searchCart()");

        try {
            List<CartResponse> cartResponses = cartService.search(itemName);

            ResponseDTO<CartResponse> response = ResponseDTO.<CartResponse>builder()
                    .data(cartResponses)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            ResponseDTO response = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    /**
     * Cart Delete
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCart(@RequestBody CartDeleteRequest cartDeleteRequest) {
        log.info("deleteCart()" + cartDeleteRequest.getNo());

        try {
            CartResponse response = cartService.delete(cartDeleteRequest.getNo());

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            ResponseDTO response = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    /**
     * Cart All Delete
     */
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllCart() {
        log.info("deleteAllCart()");

        try {
            CartResponse response = cartService.deleteAll();

            return ResponseEntity.ok(response);
        } catch (Exception e) {

            ResponseDTO response = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}
