package com.example.sathapornsunthornpan.onlinebikercommunity.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogModel {

    @SerializedName("blog_id")
    @Expose
    private String blogId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("public_date")
    @Expose
    private String publicDate;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("symbol")
    @Expose
    private String symbol;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}

