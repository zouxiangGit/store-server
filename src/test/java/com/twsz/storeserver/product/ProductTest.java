package com.twsz.storeserver.product;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.Objects;


import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_product_list_when_call_get_products_list() {
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("/api/products", Product[].class);
        Product product = Objects.requireNonNull(responseEntity.getBody())[0];
        assertThat(product.getName(), is("可乐"));
        assertThat(product.getPrice(), is(new BigDecimal("4.50")));
        assertThat(product.getUnit(), is("瓶"));
        assertThat(product.getTotalAmount(), is(10));
        assertThat(product.getImgUrl(), is("/api/img/1"));
    }

}
