package com.twsz.storeserver.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public void saveAll(List<ShoppingCartItem> shoppingCartItems){
        shoppingCartItemRepository.saveAll(shoppingCartItems);
    }
}
