package org.springframework.samples.jpetstore.domain;

public class TestUtils {

    public static Item createItem(String itemId, double price, String categoryId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setListPrice(price);
        Product product = new Product();
        product.setCategoryId(categoryId);
        item.setProduct(product);
        return item;
    }
}