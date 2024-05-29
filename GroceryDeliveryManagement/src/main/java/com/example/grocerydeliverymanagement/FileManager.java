package com.example.grocerydeliverymanagement;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String SHOPS_FILE= "shops.ser";
    private static final String USERS_FILE= "users.ser";
    private static final String RECEIPTS_FILE= "receipts.ser";

    public static void saveShops(ArrayList<Shop> shops){
        try (ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(SHOPS_FILE))) {
            oos.writeObject(shops);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Shop> loadShops() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SHOPS_FILE))) {
            return (ArrayList<Shop>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static void saveReceipts(ArrayList<Receipt> receipts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RECEIPTS_FILE))) {
            oos.writeObject(receipts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Receipt> loadReceipts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RECEIPTS_FILE))) {
            return (ArrayList<Receipt>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }
}



