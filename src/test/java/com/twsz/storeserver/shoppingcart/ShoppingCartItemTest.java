package com.twsz.storeserver.shoppingcart;

import com.twsz.storeserver.product.Product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ShoppingCartItemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_status_code_CREATED_when_call_create_shopping_cart() {
        ResponseEntity<Void> responseEntity = restTemplate
                .postForEntity("/api/shopping-cart-items", mockShoppingCartItems(), Void.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    private List<ShoppingCartItem> mockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder()
                        .product(Product.builder().id(1).build())
                        .amount(1)
                        .build(),
                ShoppingCartItem.builder()
                        .product(Product.builder().id(2).build())
                        .amount(2)
                        .build()
        );
    }
}
