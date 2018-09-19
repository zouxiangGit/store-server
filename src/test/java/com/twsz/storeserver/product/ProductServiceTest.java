package com.twsz.storeserver.product;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void should_return_product_list_when_call_get_products_list() throws Exception {
        List<Product> mockProductList = getMockProductList();
        when(productRepository.findAll()).thenReturn(mockProductList);
        List<Product> productList = productService.getProductList();
        assertArrayEquals(mockProductList.toArray(), productList.toArray());
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