package com.twsz.storeserver.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Integer> {

}
