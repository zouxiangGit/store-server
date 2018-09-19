package com.twsz.storeserver.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_return_product_list_when_call_get_all() {
        List<Product> mockProductList = getMockProductList();
        List<Product> productList = productRepository.findAll();
        Assert.assertArrayEquals(mockProductList.toArray(), productList.toArray());
    }

    private List<Product> getMockProductList() {
        return Arrays.asList(
                Product.builder()
                        .id(1)
                        .name("可乐")
                        .price(new BigDecimal("4.50"))
                        .unit("瓶")
                        .totalAmount(10)
                        .imgUrl("/api/img/1")
                        .build(),
                Product.builder()
                        .id(2)
                        .name("雪碧")
                        .price(new BigDecimal("4.50"))
                        .unit("瓶")
                        .totalAmount(10)
                        .imgUrl("/api/img/1")
                        .build());
    }

}