package com.example.cges.entity;

public class RecommendObj {
    String name;
    Integer count;

    public RecommendObj(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "RecommendObj{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public RecommendObj() {
    }
}