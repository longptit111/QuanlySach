package com.example.quanlysach.model;

import java.io.Serializable;

public class BookSold implements Serializable {
    private int id;
    private String title, price, date, author;

    public BookSold() {
    }

    public BookSold(int id, String title, String price, String date, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.date = date;
        this.author = author;
    }

    public BookSold(String title, String price, String date, String author) {
        this.title = title;
        this.price = price;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
