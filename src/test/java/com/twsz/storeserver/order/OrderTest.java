package com.twsz.storeserver.order;

import com.twsz.storeserver.product.Product;
import com.twsz.storeserver.shoppingcart.ShoppingCartItem;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrderTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Ignore
    public void should_return_status_code_CREATED_and_new_order_when_call_create_order() {
        List<ShoppingCartItem> shoppingCartItemList = getMockShoppingCartItems();
        ResponseEntity<Order> orderResponseEntity = restTemplate
                .postForEntity("/api/users/1/orders", shoppingCartItemList, Order.class);
        assertEquals(HttpStatus.CREATED,orderResponseEntity.getStatusCode());
        String locationHeader = orderResponseEntity.getHeaders().getFirst("Location");
        assertTrue(Objects.requireNonNull(locationHeader).endsWith("/api/users/1/orders/1"));
//        assertEquals(getExpectedOrder(),orderResponseEntity.getBody());
    }

    private Order getExpectedOrder(){
        return Order.builder()
                .id(1)
                .orderItems(new HashSet<>(Arrays.asList(
                        OrderItem.builder()
                                .id(1)
                                .product(Product.builder()
                                        .id(1)
                                        .name("可乐")
                                        .price(new BigDecimal("4.50"))
                                        .unit("瓶")
                                        .totalAmount(10)
                                        .imgUrl("/api/img/1").build())
                                .amount(1)
                                .build(),
                        OrderItem.builder()
                                .id(2)
                                .product(Product.builder()
                                        .id(2)
                                        .name("雪碧")
                                        .price(new BigDecimal("4.50"))
                                        .unit("瓶")
                                        .totalAmount(10)
                                        .imgUrl("/api/img/1").build())
                                .amount(2)
                                .build()
                )))
                .totalPrice(new BigDecimal("13.50"))
                .build();
    }

    private List<ShoppingCartItem> getMockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder().id(1).build(),
                ShoppingCartItem.builder().id(2).build()
        );
    }

}
