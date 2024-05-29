package com.example.grocerydeliverymanagement;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<User> users;
    private ArrayList<Shop> shops;
    private ArrayList<Receipt> receipts;

    public DataManager(){
        users = FileManager.loadUsers();
        shops = FileManager.loadShops();
        receipts = FileManager.loadReceipts();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public void loginShop(Shop shop){
        shops.add(shop);       //Used this method to fix logging in to Shop UI.
        saveShops();;
    }
    public void addShop(Shop shop) {
        for (int i=0;i<shops.size();i++) {                          //deletes the old shop from array and then add updated shop into the array.
            if (shops.get(i).getShopName().equals(shop.getShopName())) {
                shops.remove(i);
            }
        }
        shops.add(shop);
        saveShops();
    }

    public ArrayList<Shop> getShops() {
        ArrayList<Shop> uniqueShops = new ArrayList<>();          //Used this method for fixing appearing the same shop multiple time in Shop Selection UI
        for (Shop shop : shops) {
            boolean isDuplicate = false;
            for (Shop uniqueShop : uniqueShops) {
                if (uniqueShop.getShopName().equals(shop.getShopName())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueShops.add(shop);
            }
        }
        return uniqueShops;
    }


    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
        saveReceipts();
    }

    public ArrayList<Receipt> getReceiptsForCustomer(String customerName) {
        ArrayList<Receipt> customerReceipts = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (receipt.getCustomerName().equals(customerName)) {
                customerReceipts.add(receipt);
            }
        }
        return customerReceipts;
    }


    public ArrayList<Receipt> getReceiptsForShop(String shopName) {
        ArrayList<Receipt> shopReceipts = new ArrayList<>();
        for (Receipt receipt : receipts) {
            if (receipt.getShopName().equals(shopName)) {
                shopReceipts.add(receipt);
            }
        }
        return shopReceipts;
    }
    public boolean checkDuplicateAccount(String username){
        boolean notDuplicate=false;
        for (User user : users){
            if (user.getUsername().equals(username)) {
                notDuplicate = true;
                break;
            }
        }
        return notDuplicate;
    }
    public boolean checkDuplicateShop(String shopName){
        boolean notDuplicate=false;
        for(Shop shop:shops){
            if (shop.getShopName().equals(shopName)) {
                notDuplicate = true;
                break;
            }
        }
        return notDuplicate;
    }
    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    private void saveUsers() {
        FileManager.saveUsers(users);
    }
    private void saveShops() {
        FileManager.saveShops(shops);
    }
    private void saveReceipts() {
        FileManager.saveReceipts(receipts);
    }
}


