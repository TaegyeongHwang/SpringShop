package spring.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.shop.dto.ResponseDTO;
import spring.shop.dto.order.OrderResponse;
import spring.shop.service.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * Order Register
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerOrder(@RequestParam List<Integer> cart) {
        log.info("registerOrder()");

        try {
            List<OrderResponse> orderResponses = orderService.register(cart);

            ResponseDTO<OrderResponse> response = ResponseDTO.<OrderResponse>builder()
                    .data(orderResponses)
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
     * Item FindList
     */
    @GetMapping("/findList")
    public ResponseEntity<?> findListOrder() {
        log.info("findListOrder()");

        try {
            List<OrderResponse> orderResponses = orderService.findList();

            ResponseDTO<OrderResponse> response = ResponseDTO.<OrderResponse>builder()
                    .data(orderResponses)
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

}
