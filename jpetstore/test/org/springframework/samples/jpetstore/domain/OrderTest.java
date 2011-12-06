package org.springframework.samples.jpetstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @Test
    public void testInitOrder() throws Exception {
        Item item = TestUtils.createItem("1", 0d, "1");

        Order order = createOrderWithItem(item);

        assertEquals("Number of ordered items", 1, order.getLineItems().size());
    }

    @Test
    public void testDiscountedPriceIsUsedInOrder() {
        Item item = TestUtils.createItem("1", 300, "1");

        Order order = createOrderWithItem(item);

        assertEquals(270d, order.getTotalPrice(), 0);
    }

    private Order createOrderWithItem(Item item) {
        Cart cart = new Cart();
        cart.addItem(item, true);

        Order order = new Order();
        order.initOrder(new Account(), cart);
        return order;
    }
}
