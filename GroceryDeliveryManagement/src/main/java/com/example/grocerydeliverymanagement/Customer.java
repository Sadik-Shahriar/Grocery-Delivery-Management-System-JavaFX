package com.example.grocerydeliverymanagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements Serializable {
    private ArrayList<Product> cart;

    public Customer(String username, String password, String phoneNumber) {
        super(username, password, phoneNumber);
        this.cart = new ArrayList<>();
    }

    public void setCart(Product product, int availableStock) throws Exception {
        boolean productFound = false;
        for (Product cartProduct : cart) {
            if (cartProduct.getName().equals(product.getName())) {
                if (cartProduct.getStock() + product.getStock() > availableStock) {
                    throw new Exception();
                }
                cartProduct.setStock(cartProduct.getStock() + product.getStock());
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            if (product.getStock() > availableStock) {
                throw new Exception();
            }
            cart.add(new Product(product.getName(), product.getPrice(), product.getStock()));
        }
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}