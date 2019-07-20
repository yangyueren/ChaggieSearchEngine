package com.example.cges.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoObj {

    private String video_id;
    private String title;
    private String watch;
    private String subtitle;
    private String time;
    private String up;
    private String description;

    public VideoObj(){}
    public VideoObj(String video_id, String title, String watch, String subtitle, String time, String up, String description) {
        this.video_id = video_id;
        this.title = title;
        this.watch = watch;
        this.subtitle = subtitle;
        this.time = time;
        this.up = up;
        this.description = description;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
