package com.twsz.storeserver.order;

import com.twsz.storeserver.shoppingcart.ShoppingCartItem;
import com.twsz.storeserver.shoppingcart.ShoppingCartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Order add(List<ShoppingCartItem> shoppingCartItems) {
        Order order = new Order();
        Set<OrderItem> orderItems = shoppingCartItems.stream()
                .map(shoppingCartItem -> {
                    ShoppingCartItem dbItem = shoppingCartItemRepository
                            .findById(shoppingCartItem.getId())
                            .orElseThrow(() -> new RuntimeException("shopping cart item id no exist:" + shoppingCartItem.getId()));
                    return OrderItem.builder()
                            .product(dbItem.getProduct())
                            .amount(dbItem.getAmount())
                            .build();
                })
                .collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        order.setTotalPrice(totalPrice);
        return orderRepository.add(order);
    }
}
