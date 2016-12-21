package com.example.sathapornsunthornpan.onlinebikercommunity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sathapornsunthornpan on 12/20/16.
 */

public class Blog2 {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("tags")
    @Expose
    private ArrayList<Integer> tags;

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Blog2{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", img='" + img + '\'' +
                ", user_id='" + user_id + '\'' +
                ", tags=" + tags.toString() +
                '}';
    }
}
