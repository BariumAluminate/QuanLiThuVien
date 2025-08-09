package com.example.quanlithuvien;

public class Item {
    protected String id;
    protected String name;

    public Item() {

    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
