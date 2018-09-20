package com.twsz.storeserver.order;

import com.twsz.storeserver.product.Product;
import com.twsz.storeserver.shoppingcart.ShoppingCartItem;
import com.twsz.storeserver.shoppingcart.ShoppingCartItemRepository;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Test
    public void should_return_new_order_when_add_order() {
        Order mockNoIdOrder = getMockOrder(false);
        Order mockHasIdOrder = getMockOrder(true);
        when(orderRepository.add(mockNoIdOrder)).thenReturn(mockHasIdOrder);
        List<ShoppingCartItem> mockShoppingCartItems = getMockShoppingCartItems();
        when(shoppingCartItemRepository.findById(1)).thenReturn(Optional.of(mockShoppingCartItems.get(0)));
        when(shoppingCartItemRepository.findById(2)).thenReturn(Optional.of(mockShoppingCartItems.get(1)));
        Order order = orderService.add(getMockOnlyIdShoppingCartItems());
        assertEquals(mockHasIdOrder,order);
    }

    private Order getMockOrder(boolean hasId){
        return Order.builder()
                .id(hasId ? 1 : null)
                .orderItems(new HashSet<>(Arrays.asList(
                        OrderItem.builder()
                                .id(hasId ? 1 : null)
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
                                .id(hasId ? 2 : null)
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

    private List<ShoppingCartItem> getMockOnlyIdShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder().id(1).build(),
                ShoppingCartItem.builder().id(2).build()
        );
    }

    private List<ShoppingCartItem> getMockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder()
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
                ShoppingCartItem.builder()
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
        );
    }
}