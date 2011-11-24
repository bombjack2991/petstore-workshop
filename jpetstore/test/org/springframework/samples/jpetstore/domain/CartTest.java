package org.springframework.samples.jpetstore.domain;

import junit.framework.TestCase;
import org.junit.Before;

public class CartTest extends TestCase {

    private Cart cart = null;
    private Item item = null;

    public void setUp() {
        cart = new Cart();
        item = new Item();
        item.setItemId("1");
        item.setListPrice(5);
    }

    public void testAddItem() throws Exception {
        assertEquals("Number of items", 0, cart.getCartItemList().getSource().size());
        cart.addItem(item, true);
        assertEquals("Number of items", 1, cart.getCartItemList().getSource().size());
    }

    public void testRemoveItemById() throws Exception {
        cart.addItem(item, true);
        Item removedItem = cart.removeItemById(item.getItemId());
        assertEquals("Removed item", item, removedItem);
        assertEquals("Number of items", 0, cart.getCartItemList().getSource().size());
    }

    public void testGetSubTotal() throws Exception {
        cart.addItem(item, true);
        cart.addItem(item, true);

        Item catItem = new Item();
        catItem.setItemId("2");
        catItem.setListPrice(10);
        cart.addItem(catItem, true);
        cart.addItem(catItem, true);
        cart.addItem(catItem, true);

        assertEquals(40d, cart.getSubTotal());
    }

    public void testIncreaseItemQuantity() {
        cart.addItem(item, true);
        cart.incrementQuantityByItemId(item.getItemId());
        assertEquals("Number of different items in cart", 1, cart.getNumberOfItems());
        assertEquals("Sub total", 10d, cart.getSubTotal());
    }

    public void testSetSpecificItemQuantity() {
        cart.addItem(item, true);
        cart.setQuantityByItemId(item.getItemId(), 5);
        assertEquals("Sub total", 25d, cart.getSubTotal());
    }
}
