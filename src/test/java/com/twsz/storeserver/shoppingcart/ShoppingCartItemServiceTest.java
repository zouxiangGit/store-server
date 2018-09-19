package com.twsz.storeserver.shoppingcart;

import com.twsz.storeserver.product.Product;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemServiceTest {
    @InjectMocks
    private ShoppingCartItemService shoppingCartItemService;
    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Test
    public void should_save_to_db_when_call_add_all_shopping_cart_items() {
        List<ShoppingCartItem> mockShoppingCartItems = mockShoppingCartItems();
        shoppingCartItemService.saveAll(mockShoppingCartItems);
        verify(shoppingCartItemRepository, times(1))
                .saveAll(mockShoppingCartItems);
    }

    private List<ShoppingCartItem> mockShoppingCartItems() {
        return Arrays.asList(
                ShoppingCartItem.builder()
                        .userId(1)
                        .product(Product.builder().id(1).build())
                        .amount(1)
                        .build(),
                ShoppingCartItem.builder()
                        .userId(1)
                        .product(Product.builder().id(2).build())
                        .amount(2)
                        .build()
        );
    }
}