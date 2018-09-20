package com.twsz.storeserver.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twsz.storeserver.product.Product;
import com.twsz.storeserver.shoppingcart.ShoppingCartItem;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void should_return_status_code_CREATED_and_new_order_when_call_create_order() throws Exception {
        List<ShoppingCartItem> mockShoppingCartItems = getMockShoppingCartItems();
        when(orderService.add(mockShoppingCartItems)).thenReturn(getExpectedOrder());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/users/1/orders")
                .content(new ObjectMapper().writeValueAsString(mockShoppingCartItems))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location"
                        , endsWith("/api/users/1/orders/2")
                ));

    }

    private List<ShoppingCartItem> getMockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder().id(1).build(),
                ShoppingCartItem.builder().id(2).build()
        );
    }

    private Order getExpectedOrder(){
        return Order.builder()
                .id(2)
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
}