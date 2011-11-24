package org.springframework.samples.jpetstore.domain;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class CartItemTest extends TestCase {

    public void testGetTotalPrice() throws Exception {
        Item item = new Item();
        item.setListPrice(10d);
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(2);
        assertEquals(20d, cartItem.getTotalPrice());
    }
}
