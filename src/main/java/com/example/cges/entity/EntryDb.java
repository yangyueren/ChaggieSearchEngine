package com.example.cges.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
@Setter
@Getter
@Document(indexName = "chageng", type = "EntryDb", shards = 5,replicas = 1)
public class EntryDb implements Comparable<EntryDb> {

    @Id
    @Field(type = FieldType.Long)
    private Long id;


    private String name;

    private String time;
    private String image;
    private Integer view;
    private Integer like;
    private Integer dislike;

    private List<String> tagList;

    private String content;

    private List<VideoObj> videoList;

    private List<String> imageList;

    private List<WeiboObj> weiboList;
    private String wordCloud;

    @Override
    public int compareTo(EntryDb o) {
        return 0;
    }

    @Override
    public String toString() {
        return "EntryDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", image='" + image + '\'' +
                ", view=" + view +
                ", like=" + like +
                ", dislike=" + dislike +
                ", tagList=" + tagList +
                ", content='" + content + '\'' +
                ", videoList=" + videoList +
                ", imageList=" + imageList +
                ", weiboList=" + weiboList +
                ", wordCloud='" + wordCloud + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<VideoObj> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoObj> videoList) {
        this.videoList = videoList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<WeiboObj> getWeiboList() {
        return weiboList;
    }

    public void setWeiboList(List<WeiboObj> weiboList) {
        this.weiboList = weiboList;
    }

    public String getWordCloud() {
        return wordCloud;
    }

    public void setWordCloud(String wordCloud) {
        this.wordCloud = wordCloud;
    }

    public EntryDb(Long id, String name, String time, String image, Integer view, Integer like, Integer dislike, List<String> tagList, String content, List<VideoObj> videoList, List<String> imageList, List<WeiboObj> weiboList, String wordCloud) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.image = image;
        this.view = view;
        this.like = like;
        this.dislike = dislike;
        this.tagList = tagList;
        this.content = content;
        this.videoList = videoList;
        this.imageList = imageList;
        this.weiboList = weiboList;
        this.wordCloud = wordCloud;
    }
    public EntryDb(EntryFatDb entryFatDb){
        this.id = entryFatDb.getId();
        this.name = entryFatDb.getName();
        this.time = entryFatDb.getTime();
        this.image = entryFatDb.getImage();
        this.view = entryFatDb.getView();
        this.like = entryFatDb.getLike();
        this.dislike = entryFatDb.getDislike();
        this.tagList = entryFatDb.getTagList();
        this.content = entryFatDb.getContent();
        this.videoList = entryFatDb.getVideoList();
        this.imageList = entryFatDb.getImageList();
        this.weiboList = entryFatDb.getWeiboList();
        this.wordCloud = entryFatDb.getWordCloud();
    }

    public EntryDb() {
    }
}
