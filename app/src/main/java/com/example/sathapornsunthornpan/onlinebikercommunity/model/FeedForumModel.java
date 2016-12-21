package com.example.sathapornsunthornpan.onlinebikercommunity.model;


public class FeedForumModel {
    private String forum_title;
    private String forum_detail;
    private String created_at;
    private String name;

    public String getTitle() {
        return forum_title;
    }

    public void setTitle(String forum_title) {
        this.forum_title = forum_title;
    }

    public String getDate() {
        return created_at;
    }

    public void setDate(String created_at) {
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return forum_detail;
    }

    public void setDetail(String forum_detail) {
        this.forum_detail = forum_detail;
    }


}
