package org.springframework.samples.jpetstore.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.springframework.samples.jpetstore.domain.TestUtils.*;

public class CartTest {

    private Cart cart = null;
    private Item defaultItem = null;

    @Before
    public void setUp() {
        cart = new Cart();
        defaultItem = createItem("1", 5, "1");
    }

    @Test
    public void testAddItem() throws Exception {
        assertEquals("Number of items", 0, cart.getCartItemList().getSource().size());
        cart.addItem(defaultItem, true);
        assertEquals("Number of items", 1, cart.getCartItemList().getSource().size());
    }

    @Test
    public void testRemoveItemById() throws Exception {
        cart.addItem(defaultItem, true);
        Item removedItem = cart.removeItemById(defaultItem.getItemId());
        assertEquals("Removed item", defaultItem, removedItem);
        assertEquals("Number of items", 0, cart.getCartItemList().getSource().size());
    }

    @Test
    public void testGetSubTotal() throws Exception {
        cart.addItem(defaultItem, true);
        cart.addItem(defaultItem, true);

        Item catItem = createItem("2", 10, "1");
        cart.addItem(catItem, true);
        cart.addItem(catItem, true);
        cart.addItem(catItem, true);

        assertEquals(40d, cart.getSubTotal(), 0);
    }

    @Test
    public void testIncreaseItemQuantity() {
        cart.addItem(defaultItem, true);
        cart.incrementQuantityByItemId(defaultItem.getItemId());
        assertEquals("Number of different items in cart", 1, cart.getNumberOfItems());
        assertEquals("Sub total", 10d, cart.getSubTotal(), 0);
    }

    @Test
    public void testSetSpecificItemQuantity() {
        cart.addItem(defaultItem, true);
        cart.setQuantityByItemId(defaultItem.getItemId(), 5);
        assertEquals("Sub total", 25d, cart.getSubTotal(), 0);
    }

    @Test
    public void testSubTotalMoreThan200() {
        defaultItem.setListPrice(50d);
        cart.addItem(defaultItem, true);
        cart.setQuantityByItemId(defaultItem.getItemId(), 4);
        assertTrue("Discount should be applicable", cart.isDiscountApplicable());
    }

    @Test
    public void testNoDiscountForCheapOrders() {
        defaultItem.setListPrice(199);
        cart.addItem(defaultItem, true);
        assertFalse(cart.isDiscountApplicable());
    }

    @Test
    public void testDiscount5AndMoreSameItems() {
        cart.addItem(defaultItem, true);
        cart.setQuantityByItemId(defaultItem.getItemId(), 5);
        assertTrue(cart.isDiscountApplicable());
    }

    @Test
    public void testDiscountForFiveAndMoreDifferentItems() {
        cart.addItem(defaultItem, true);
        cart.setQuantityByItemId(defaultItem.getItemId(), 4);
        cart.addItem(createItem("2", 10, "1"), true);
        assertTrue(cart.isDiscountApplicable());
    }

    @Test
    public void testDiscountForSeveralCategories() {
        cart.addItem(defaultItem, true);
        cart.setQuantityByItemId(defaultItem.getItemId(), 2);
        Item anotherCategoryItem = createItem("2", 20, "2");
        cart.addItem(anotherCategoryItem, true);
        cart.setQuantityByItemId(anotherCategoryItem.getItemId(), 2);
        assertTrue(cart.isDiscountApplicable());
    }

    @Test
    public void testNoDiscountForEmptyCart() {
        assertFalse(cart.isDiscountApplicable());
    }

    @Test
    public void testNoVoucherGivenForCheapOrders() {
        defaultItem.setListPrice(999);
        cart.addItem(defaultItem, true);
        assertFalse(cart.isVoucherGiven());
    }

    @Test
    public void testVoucherGivenForSubTotal1000AndMore() {
        defaultItem.setListPrice(1000);
        cart.addItem(defaultItem, true);
        assertTrue(cart.isVoucherGiven());
    }

    @Test
    public void testGetDiscountedPriceWhenDiscountApplied() {
        defaultItem.setListPrice(200);
        cart.addItem(defaultItem, true);
        assertEquals(180d, cart.getDiscountedSubTotal(), 0);
    }

    @Test
    public void testDiscountPriceIsTheSameWhenDiscountIsNotApplicable() {
        defaultItem.setListPrice(50);
        cart.addItem(defaultItem, true);
        assertEquals(50d, cart.getDiscountedSubTotal(), 0);
    }


}
