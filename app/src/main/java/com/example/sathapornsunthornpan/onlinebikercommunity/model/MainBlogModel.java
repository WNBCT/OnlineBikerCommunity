package com.example.sathapornsunthornpan.onlinebikercommunity.model;


import java.util.List;

public class MainBlogModel {

    public int count;

    public List<BlogModel> result;

    public MainBlogModel(int count, List<BlogModel> result) {
        this.count = count;
        this.result = result;
    }

    @Override
    public String toString() {
        return "MainBlogModel{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
