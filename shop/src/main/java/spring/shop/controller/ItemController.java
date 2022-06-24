package spring.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.shop.dto.ResponseDTO;
import spring.shop.dto.item.ItemRequest;
import spring.shop.dto.item.ItemResponse;
import spring.shop.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    /**
     * Item Register
     */
    @PostMapping("register")
    public ResponseEntity<?> registerItem(@RequestBody ItemRequest itemRequest) {
        log.info("registerItem()");

        try {
            ItemResponse response = itemService.register(itemRequest);

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
    public ResponseEntity<?> findListItem() {
        log.info("findListItem()");

        try {
            List<ItemResponse> itemResponses = itemService.findList();

            ResponseDTO<ItemResponse> response = ResponseDTO.<ItemResponse>builder()
                    .data(itemResponses)
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
     * Item Search
     */
    @GetMapping("/search/{itemName}")
    public ResponseEntity<?> searchItem(@PathVariable String itemName) {
        log.info("searchItem()");

        try {
            List<ItemResponse> itemResponses = itemService.search(itemName);

            ResponseDTO<ItemResponse> response = ResponseDTO.<ItemResponse>builder()
                    .data(itemResponses)
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
