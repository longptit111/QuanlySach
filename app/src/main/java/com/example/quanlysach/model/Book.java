package com.example.quanlysach.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title, price, category, nxb, author;

    public Book() {
    }

    public Book(int id, String title, String price, String category, String nxb, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.nxb = nxb;
        this.author = author;
    }

    public Book(String title, String price, String category, String nxb, String author) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.nxb = nxb;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
