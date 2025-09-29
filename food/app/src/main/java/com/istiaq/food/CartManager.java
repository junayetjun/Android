package com.istiaq.food;

import com.istiaq.food.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    // Singleton instance
    private static CartManager instance;

    // List to hold cart items
    private final List<CartItem> cartItems;

    // List to hold last placed order
    private final List<CartItem> lastOrder;

    // Private constructor
    private CartManager() {
        cartItems = new ArrayList<>();
        lastOrder = new ArrayList<>();
    }

    // Get singleton instance
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Add item to cart
    public void addItem(CartItem item) {
        for (CartItem existing : cartItems) {
            if (existing.getName().equals(item.getName())) {
                existing.setQuantity(existing.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(item);
    }

    // Get all items in cart
    public List<CartItem> getItems() {
        return cartItems;
    }

    // Get total price of cart
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Clear the cart
    public void clearCart() {
        cartItems.clear();
    }

    // Save the current cart as the last order
    public void saveLastOrder() {
        lastOrder.clear();
        for (CartItem item : cartItems) {
            lastOrder.add(new CartItem(item.getName(), item.getPrice(), item.getQuantity()));
        }
    }

    // Get the last order
    public List<CartItem> getLastOrder() {
        return lastOrder;
    }

    // Place order (save last order and clear cart)
    public void placeOrder() {
        saveLastOrder();
        clearCart();
    }
}
