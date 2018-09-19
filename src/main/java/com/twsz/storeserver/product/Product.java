package com.twsz.storeserver.product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Product {
    @Id
    private Integer id;
    private String name;
    private BigDecimal price;
    private String unit;
    private Integer totalAmount;
    private String imgUrl;
}
