package com.example.cges.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WeiboObj {
    private String mid;
    private String user;
    private String avator;
    private String content;
    private String time;
    private String like;
    private String repost;
    private String comment;
    private String source;
    private List<String> imgList;

    public WeiboObj() {
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getRepost() {
        return repost;
    }

    public void setRepost(String repost) {
        this.repost = repost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public WeiboObj(String mid, String user, String avator, String content, String time, String like, String repost, String comment, String source, List<String> imgList) {
        this.mid = mid;
        this.user = user;
        this.avator = avator;
        this.content = content;
        this.time = time;
        this.like = like;
        this.repost = repost;
        this.comment = comment;
        this.source = source;
        this.imgList = imgList;
    }
}
