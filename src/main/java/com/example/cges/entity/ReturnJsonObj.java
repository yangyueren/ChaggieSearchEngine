package com.example.cges.entity;

import java.util.List;

public class ReturnJsonObj {
    public String code;
    public String msg;
    public List<EntryDb> data;

    public ReturnJsonObj(String code, String msg, List<EntryDb> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ReturnJsonObj(List<EntryDb> data){
        this("0","请求成功",data);
    }

    public ReturnJsonObj(){
        code = "0";
        msg = "请求成功";
        data = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ReturnJsonObj{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EntryDb> getData() {
        return data;
    }

    public void setData(List<EntryDb> data) {
        this.data = data;
    }


}
