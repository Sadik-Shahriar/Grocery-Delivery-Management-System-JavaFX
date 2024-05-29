package com.example.grocerydeliverymanagement;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

public class Receipt implements Serializable {
    private String shopName;
    private ArrayList<Product> products;
    private String customerName;
    private String customerPhoneNumber;
    private String deliveryAddress;
    private Date orderDate;
    private double totalCost=0;
    public Receipt(String shopName, ArrayList<Product> products, String customerName,String customerPhoneNumber, String deliveryAddress,double totalCost) {
        this.shopName = shopName;
        this.products = products;
        this.customerName = customerName;
        this.customerPhoneNumber=customerPhoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = new Date();
        this.totalCost=totalCost;
    }
    public String getShopName() {
        return shopName;
    }
    public String getCustomerName(){
        return customerName;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shop Name: ").append(shopName).append("\n");
        sb.append("Order Date: ").append(orderDate).append("\n");
        sb.append("Customer: ").append(customerName).append("\n");
        sb.append("Phone Number: ").append(customerPhoneNumber).append("\n");
        sb.append("Delivery Address: ").append(deliveryAddress).append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        sb.append("Total cost: ").append(totalCost).append(" BDT \n");
        return sb.toString();
    }
}
