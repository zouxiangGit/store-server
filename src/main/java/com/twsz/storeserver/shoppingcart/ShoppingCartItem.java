package com.twsz.storeserver.shoppingcart;

import com.twsz.storeserver.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {
    private Product product;
    private Integer amount;
}
