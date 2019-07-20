package com.example.cges.controller;

import com.example.cges.entity.ReturnJsonObj;
import com.example.cges.myutils.MyTools;
import com.example.cges.mongoentity.UncheckedEntryDb;
import com.example.cges.service.IMongoServ;
import com.example.cges.service.IPServ;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class MongoAPIController {

    private IMongoServ iMongoServ;
    private IPServ ipServ;
    private MyTools myTools;


    @Autowired
    public MongoAPIController(IMongoServ iMongoServ, IPServ ipServ) {
        this.iMongoServ = iMongoServ;
        this.ipServ = ipServ;
        myTools = new MyTools();
    }
    
    @RequestMapping("/api/v3/query")
    public ReturnJsonObj query(HttpServletRequest request) {
        System.out.println(iMongoServ.findAll().get(0).toString());
        System.out.println();
        String ip = myTools.getIpAddr(request);

        return new ReturnJsonObj();
    }

    @RequestMapping("/api/v3/create")
    public ReturnJsonObj create(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            String ip = myTools.getIpAddr(request);

            String name = params.get("name").toString();
            String tag_list = params.get("tag_list").toString();
            String content = params.get("content").toString();
            String imgUrl = params.get("img_url").toString();
            JSONArray json = JSONArray.fromObject(tag_list);
            List<String> taglist = (List<String>) JSONArray.toCollection(json, String.class);
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            String time = ft.format(dNow);

            Long id = myTools.generateUniqueId();

            UncheckedEntryDb uncheckedEntryDb = new UncheckedEntryDb(id, name, time, imgUrl, 0,0,0, taglist, content, null, null, null, "");
            iMongoServ.saveEntry(uncheckedEntryDb);
            ipServ.ipVisit(ip, name, ipServ.getINSERT());

            return new ReturnJsonObj("0", "创建成功", null);
        } catch (Exception e) {
            return new ReturnJsonObj("-1", "请求失败", null);
        }
    }
}
