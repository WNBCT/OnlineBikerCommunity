package com.example.sathapornsunthornpan.onlinebikercommunity.blog;



public class Blog {

    public String id;
    public String name;


    public Blog(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
