package com.istiaq.food.model;

public class CartItem {

    private String name;
    private double price;
    private int quantity;

    // Constructor
    public CartItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Overloaded constructor (default quantity = 1)
    public CartItem(String name, double price) {
        this(name, price, 1);
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Helper: increase quantity
    public void incrementQuantity() {
        this.quantity++;
    }

    // Helper: decrease quantity (min 1)
    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }

    // toString (optional for debugging)
    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
