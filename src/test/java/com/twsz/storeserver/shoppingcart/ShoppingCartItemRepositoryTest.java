package com.twsz.storeserver.shoppingcart;

import com.twsz.storeserver.product.Product;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ShoppingCartItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Test
    public void should_save_to_db_when_call_save_all_shopping_cart_item() {
        List<ShoppingCartItem> mockShoppingCartItems = mockShoppingCartItems();
        shoppingCartItemRepository.saveAll(mockShoppingCartItems);
        mockShoppingCartItems
                .forEach(item -> item.setProduct(entityManager.find(Product.class, item.getProduct().getId())));
        assertEquals(
                mockShoppingCartItems.get(0),
                entityManager.find(ShoppingCartItem.class, 1)
        );
        assertEquals(
                mockShoppingCartItems.get(1),
                entityManager.find(ShoppingCartItem.class, 2)
        );
    }

    private List<ShoppingCartItem> mockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder()
                        .id(1)
                        .userId(1)
                        .product(Product.builder().id(1).build())
                        .amount(1)
                        .build(),
                ShoppingCartItem.builder()
                        .id(2)
                        .userId(1)
                        .product(Product.builder().id(2).build())
                        .amount(2)
                        .build()
        );
    }
}