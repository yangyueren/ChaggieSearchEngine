package com.example.cges.entity;

import java.util.List;

public class EntryObj {
    private String name;
    private Long id;
    private String time;
    private String img;
    private Integer view;
    private Integer like;
    private Integer dislike;
    private List<String> tags;
    private String content;

    public EntryObj(Long id, String name, String time, String img, Integer view, Integer like, Integer dislike, List<String> tags, String content) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.img = img;
        this.view = view;
        this.like = like;
        this.dislike = dislike;
        this.tags = tags;
        this.content = content;
    }

    public EntryObj() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}