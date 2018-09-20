package com.twsz.storeserver.order;

import com.twsz.storeserver.shoppingcart.ShoppingCartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@PathVariable("userId") Integer userId,
                                      @RequestBody List<ShoppingCartItem> shoppingCartItems,
                                      UriComponentsBuilder uriBuilder) {
        Order order = orderService.add(shoppingCartItems);
        return ResponseEntity
                .created(uriBuilder
                        .path("/api/users/{userId}/orders/{orderId}")
                        .build(userId,order.getId()))
                .build();
    }
}
