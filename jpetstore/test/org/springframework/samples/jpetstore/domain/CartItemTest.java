package org.springframework.samples.jpetstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartItemTest {

    @Test
    public void testGetTotalPrice() throws Exception {
        Item item = new Item();
        item.setListPrice(10d);
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(2);
        assertEquals(20d, cartItem.getTotalPrice(), 0);
    }
}
