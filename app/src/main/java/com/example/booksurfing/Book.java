package com.example.booksurfing;

public class Book {
    private String Author;
    private String Title;
    private String Owner;
    private String Borrowed;

    public Book(String a, String t, String o, String b) {
        this.Author = a;
        this.Title = t;
        this.Owner = o;
        this.Borrowed = b;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String a) {
        this.Author = a;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String t) {
        this.Title = t;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String o) {
        this.Owner = o;
    }

    public String getBorrowed() {
        return Borrowed;
    }

    public void setBorrowed(String b) {
        this.Borrowed = b;
    }
}
