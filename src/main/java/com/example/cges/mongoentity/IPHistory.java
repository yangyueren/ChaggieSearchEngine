package com.example.cges.mongoentity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class IPHistory {
    @Id
    private String _id;
    @Indexed
    private String ip;
    private String name;
    private Integer vote;//this is the mode, 1->vote the entry 2->create entry  3->visit the entry
    private Date date;

    public IPHistory() {
    }

    public IPHistory(String ip, String name, Integer mode) {
        this.ip = ip;
        this.name = name;
        this.vote = mode;
        this.date = new Date();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
