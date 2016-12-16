package com.example.sathapornsunthornpan.onlinebikercommunity.model;



public class BlogModel {

    public String id;
    public String name;


    public BlogModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlogModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
