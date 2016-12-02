package com.example.sathapornsunthornpan.onlinebikercommunity.blog;


import java.util.List;

public class MainBlog {

    public int count;

    public List<Blog> result;

    public MainBlog(int count, List<Blog> result) {
        this.count = count;
        this.result = result;
    }

    @Override
    public String toString() {
        return "MainBlog{" +
                "count=" + count +
                ", result=" + result +
                '}';
    }
}
