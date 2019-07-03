package com.htwg.booksurfing;

public class Book {
    private int id;
    private String Author;
    private String Title;
    private String Owner;
    private String Rating;
    private String ThumbnailSmall;
    private String Thumbnail;
    private String pageCount;
    private String ownerName;

    public Book(int id, String a, String t, String o, String r, String ts, String tl, String pc, String oN) {
        this.id = id;
        this.Author = a;
        this.Title = t;
        this.Owner = o;
        this.Rating = r;
        this.ThumbnailSmall = ts;
        this.Thumbnail = tl;
        this.pageCount = pc;
        this.ownerName = oN;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRating() {
        return Rating;
    }

    public void setRating(String r) {
        this.Rating = r;
    }

    public String getThumbnailSmall() {
        return ThumbnailSmall;
    }

    public void setThumbnailSmall(String t) {
        this.ThumbnailSmall = t;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String t) {
        this.Thumbnail = t;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pc) {
        this.pageCount= pc;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String oN) {
        this.ownerName= oN;
    }
}
