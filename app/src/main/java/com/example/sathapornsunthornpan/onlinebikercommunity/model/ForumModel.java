package com.example.sathapornsunthornpan.onlinebikercommunity.model;


import java.util.List;

public class ForumModel {
    private String name;
    private String link;
    private String count;
    private List<FeedForumModel> feed = null;

    public String getName() {
        return name;
    }

    public void setName(String credit) {
        this.name = credit;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FeedForumModel> getFeed() {
        return feed;
    }

    public void setFeed(List<FeedForumModel> feed) {
        this.feed = feed;
    }
}
