package com.example.sathapornsunthornpan.onlinebikercommunity.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListsBlogModel {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("blog")
    @Expose
    private List<BlogModel> blog = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BlogModel> getBlog() {
        return blog;
    }

    public void setBlog(List<BlogModel> blog) {
        this.blog = blog;
    }


}