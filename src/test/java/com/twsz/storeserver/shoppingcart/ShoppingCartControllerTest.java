package com.twsz.storeserver.shoppingcart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twsz.storeserver.product.Product;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartItemService shoppingCartItemService;

    @Test
    public void should_return_status_code_CREATED_when_call_create_shopping_cart() throws Exception {
        List<ShoppingCartItem> mockShoppingCartItems = mockShoppingCartItems();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/shopping-cart-items")
                .characterEncoding("utf-8")
                .content(new ObjectMapper().writeValueAsString(mockShoppingCartItems))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isCreated());
        verify(shoppingCartItemService,times(1)).saveAll(mockShoppingCartItems);
    }

    private List<ShoppingCartItem> mockShoppingCartItems() throws JsonProcessingException {
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
