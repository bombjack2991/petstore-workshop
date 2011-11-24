package org.springframework.samples.jpetstore.domain;

import junit.framework.TestCase;
import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: troggy
 * Date: 24.11.11
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class CartTest extends TestCase {

    private Cart cart = null;
    private Item item = null;

    @Before
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
}
