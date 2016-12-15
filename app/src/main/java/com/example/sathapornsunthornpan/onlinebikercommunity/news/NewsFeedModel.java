package com.example.sathapornsunthornpan.onlinebikercommunity.news;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeedModel {
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("feed")
    @Expose
    private List<FeedModel> feed = null;

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FeedModel> getFeed() {
        return feed;
    }

    public void setFeed(List<FeedModel> feed) {
        this.feed = feed;
    }
}
