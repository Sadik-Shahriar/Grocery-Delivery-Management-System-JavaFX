package com.example.grocerydeliverymanagement;


import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;
    private int stock;

    public Product(String name,double price,int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public double getPrice(){
        return price;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public int getStock(){
        return stock;
    }

    @Override
    public String toString(){
        return name+" "+price+ " BDT"+" - Stock: " + stock;
    }
}


