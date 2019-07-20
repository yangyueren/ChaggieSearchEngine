package com.example.cges.entity;

import java.util.List;
import java.util.Map;

public class RecommendJsonObj {
    public String code;
    public String msg;
    public List<RecommendObj> data;

    public RecommendJsonObj(String code, String msg, List<RecommendObj> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RecommendObj> getData() {
        return data;
    }

    public void setData(List<RecommendObj> data) {
        this.data = data;
    }

    public RecommendJsonObj() {
    }
}
