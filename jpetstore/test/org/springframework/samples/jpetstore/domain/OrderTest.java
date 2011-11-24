package org.springframework.samples.jpetstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @Test
    public void testInitOrder() throws Exception {
        Item item = new Item();
        item.setItemId("1");

        Cart cart = new Cart();
        cart.addItem(item, true);

        Account account = new Account();

        Order order = new Order();
        order.initOrder(account, cart);

        assertEquals("Number of ordered items", 1, order.getLineItems().size());
    }
}
