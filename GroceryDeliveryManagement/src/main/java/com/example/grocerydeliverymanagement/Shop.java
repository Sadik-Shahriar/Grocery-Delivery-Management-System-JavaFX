package com.example.grocerydeliverymanagement;
import java.io.Serializable;
import java.util.ArrayList;

public class Shop extends User implements Serializable {
    private String shopName;
    private ArrayList<Product> products;

    public Shop(String username, String password, String phoneNumber, String shopName) {
        super(username, password, phoneNumber);
        this.shopName = shopName;
        this.products = new ArrayList<>();
    }

    public String getShopName() {
        return shopName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        boolean productFound = false;
        for (Product existingProduct : products) {
            if (existingProduct.getName().equals(product.getName())) {
                existingProduct.setStock(existingProduct.getStock() + product.getStock());
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            products.add(product);
        }
    }

    public void removeProduct(String productName) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                products.remove(i);
                break;
            }
        }
    }

    public void updateStock(String productName, int stockChange) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setStock(product.getStock() - stockChange);
                break;
            }
        }
    }

    public int getProductStock(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product.getStock();
            }
        }
        return 0;
    }
}

