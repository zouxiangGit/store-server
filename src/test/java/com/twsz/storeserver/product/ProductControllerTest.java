package com.twsz.storeserver.product;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void should_return_product_list_when_call_get_products_list() throws Exception {
        when(productService.getProductList()).thenReturn(getMockProductList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("可乐"))
                .andExpect(jsonPath("$[1].name").value("雪碧"));
    }

    private List<Product> getMockProductList() {
        return Arrays.asList(
                Product.builder()
                        .name("可乐")
                        .price(new BigDecimal("4.5"))
                        .unit("瓶")
                        .totalAmount(10)
                        .imgUrl("/api/img/1")
                        .build(),
                Product.builder()
                        .name("雪碧")
                        .price(new BigDecimal("4.5"))
                        .unit("瓶")
                        .totalAmount(10)
                        .imgUrl("/api/img/1")
                        .build());
    }
}