package com.twsz.storeserver.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users/{userId}/shopping-cart-items")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping
    public ResponseEntity<?> createShoppingCartItem(@PathVariable("userId") Integer userId, @RequestBody List<ShoppingCartItem> shoppingCartItemList) {
        shoppingCartItemList.forEach(item -> item.setUserId(userId));
        shoppingCartItemService.saveAll(shoppingCartItemList);
        return ResponseEntity.status(CREATED).body(null);
    }
}
