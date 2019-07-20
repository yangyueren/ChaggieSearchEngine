package com.example.cges.controller;

import com.example.cges.entity.*;

import com.example.cges.myutils.MyTools;
import com.example.cges.service.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class Search {
    private IEntryServ iEntryServ;
    private FatDbServ fatDbServ;
    private IPServ ipServ;
    private MyTools myTools;
    private final Integer ONE_PAGE_NUM = 10;
    private final String TIME_BEGIN = "1995-09-09";
    private final String TIME_END = "2025-09-09";

    @Autowired
    public Search(IEntryServ iEntryServ, IPServ ipServ, FatDbServ fatDbServ) {
        this.iEntryServ = iEntryServ;
        this.ipServ = ipServ;
        this.fatDbServ = fatDbServ;
        this.myTools = new MyTools(this.iEntryServ, this.fatDbServ);
    }

    public Search() {
    }

    @RequestMapping("/api/v3/input")
    public List<EntryDb> putData() {
        try {
            myTools.fireInHole();


            return null;
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("/api/v3/recommend")
    public ReturnJsonObj recommend(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        try {
            String ip = myTools.getIpAddr(request);
            String input = params.get("input").toString();
            String limit = params.get("limit").toString();
            List<EntryDb> data = new ArrayList<>();
            if (input.equals("")) {
                Pageable pageable2 = PageRequest.of(Integer.parseInt("0"), Integer.parseInt(limit));
                Page<EntryDb> entryDbs2 = iEntryServ.findTop10ByView(input, pageable2);
                data = entryDbs2.getContent();
            } else {

                Pageable pageable = PageRequest.of(Integer.parseInt("0"), Integer.parseInt(limit));

                Page<EntryDb> entryDbs1 = iEntryServ.recommend(input, pageable);
                data = entryDbs1.getContent();
            }
            ReturnJsonObj returnJsonObj = new ReturnJsonObj(data);
            return returnJsonObj;
        } catch (Exception e) {

            return new ReturnJsonObj("0", "请求失败", null);

        }
    }

    @PostMapping("/api/v3/search")
    @ResponseBody
    public ReturnJsonObj search(@RequestParam Map<String, Object> params) {
        try {
        String key = params.get("key").toString();
        String pageNumber = params.get("page_number").toString();
        String pageSize = params.get("page_size").toString();
        String orderBy = params.get("order_by").toString();

        List<String> orderBys = null;

        if (!orderBy.equals("")){
            JSONArray json = JSONArray.fromObject(orderBy);
            orderBys = (List<String>) JSONArray.toCollection(json, String.class);
        }

        String timeBegin = params.get("time_begin").toString();
        String timeEnd = params.get("time_end").toString();
        String tag = params.get("tag").toString();

        timeBegin = timeBegin != "" ? timeBegin : TIME_BEGIN;
        timeEnd = timeBegin != "" ? TIME_END : timeEnd;
        List<EntryDb> data = new ArrayList<>();
        Pageable pageable = null;
        pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.valueOf(pageSize));

//            System.out.println(tag);
        if (tag != "") {
            Page<EntryDb> entryDbs1 = iEntryServ.search(key, tag, pageable, timeBegin, timeEnd);
            data = entryDbs1.getContent();
        } else {
            data = this.searchDifferentMethods(key, pageable, timeBegin, timeEnd);
        }
        data = myTools.sortData(data, orderBys);
        return new ReturnJsonObj(data);
        } catch (Exception e) {
            return new ReturnJsonObj("-1", "请求失败", null);
        }

    }

//    public static void main(String[] args) {
//        String content = "英国\"足球\"";
//
//        Pattern pattern = Pattern.compile("\"(.*?)\"");
//        Matcher matcher = pattern.matcher(content);
//        System.out.println(content);
//        matcher.find();
//
//        String f = matcher.group();
//        content = content.replace(f, "");
//        System.out.println(content);
//        f = f.replace("\"", "");
//        System.out.println(f);
//    }

    public List<EntryDb> searchDifferentMethods(String content, Pageable pageable, String timeBegin, String timeEnd) {
        List<EntryDb> data = null;

        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(content);
        Pattern pattern1 = Pattern.compile("-(.*?)-");
        Matcher matcher1 = pattern1.matcher(content);
        boolean quote = matcher.find();
        boolean minus = matcher1.find();
//        System.out.println(minus);
        if (quote && minus) {
            String quoteMark = matcher.group();
            content = content.replace(quoteMark, "");
            quoteMark = quoteMark.replace("\"", "");
            String minusMark = matcher1.group();
            content = content.replace(minusMark, "");
            minusMark = minusMark.replace("-", "");
//            System.out.println(quoteMark+minusMark);
            data = this.searchQuoteMark(quoteMark, minusMark, content, pageable, timeBegin, timeEnd);
        } else if (quote) {
            String quoteMark = matcher.group();
            content = content.replace(quoteMark, "");
            quoteMark = quoteMark.replace("\"", "");

//            System.out.println("quo" + quoteMark);
            data = this.searchQuoteMark(quoteMark, "", content, pageable, timeBegin, timeEnd);
        } else if (minus) {
            String minusMark = matcher1.group();
            content = content.replace(minusMark, "");
//            System.out.println(minusMark);
            minusMark = minusMark.replace("-", "");
            data = this.searchQuoteMark("", minusMark, content, pageable, timeBegin, timeEnd);
//            System.out.println(data.toString());
        } else if (content.length() > 15 || content.contains(" ")) {
            content.replace(" ", "");
//            System.out.println("kong"+content);
            data = this.searchSentence(content, pageable, timeBegin, timeEnd);
        } else {

            data = this.searchKey(content, pageable, timeBegin, timeEnd);
        }
        return data;
    }

    public List<EntryDb> searchKey(String key, Pageable pageable, String timeBegin, String timeEnd) {
        List<EntryDb> entryDbs = this.iEntryServ.search(key, pageable, timeBegin, timeEnd).getContent();
        return entryDbs;
    }

    public List<EntryDb> searchSentence(String sentence, Pageable pageable, String timeBegin, String timeEnd) {
        Page<EntryDb> entryDbs1 = iEntryServ.searchSentence(sentence, pageable, timeBegin, timeEnd);
        List<EntryDb> data = entryDbs1.getContent();
        return data;
    }

    public List<EntryDb> searchQuoteMark(String quoteMark, String minusMark, String sentence, Pageable pageable, String timeBegin, String timeEnd) {

        Page<EntryDb> entryDbs1 = iEntryServ.searchQuoteMark(quoteMark, minusMark, sentence, pageable, timeBegin, timeEnd);
        List<EntryDb> data = entryDbs1.getContent();
        return data;
    }

    @PostMapping("/api/v3/entry")
    @ResponseBody
    public ReturnJsonObj entryInfo(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        try {
            String ip = myTools.getIpAddr(request);

            Long id = Long.parseLong(params.get("id").toString());
            Integer video_limit = Integer.parseInt(params.get("video_limit").toString());
            Integer img_limit = Integer.parseInt(params.get("img_limit").toString());
            Integer weibo_limit = Integer.parseInt(params.get("weibo_limit").toString());

            EntryDb entryDb = fatDbServ.findById(id);
            ipServ.ipVisit(ip, entryDb.getName(), ipServ.getINSERT());

            if (entryDb.getVideoList() != null && entryDb.getVideoList().size() > video_limit) {
                List<VideoObj> videoObjs = entryDb.getVideoList().subList(0, video_limit);
                entryDb.setVideoList(videoObjs);
            }
            if (entryDb.getImageList() != null && entryDb.getImageList().size() > img_limit) {
                List<String> imgs = entryDb.getImageList().subList(0, img_limit);
                entryDb.setImageList(imgs);
            }

            if (entryDb.getWeiboList() != null && entryDb.getWeiboList().size() > weibo_limit) {
                List<WeiboObj> weiboObjs = entryDb.getWeiboList().subList(0, weibo_limit);
                entryDb.setWeiboList(weiboObjs);
            }

            List<EntryDb> data = Arrays.asList(entryDb);
            entryDb.setView(entryDb.getView() + 1);
            iEntryServ.update(entryDb);
            fatDbServ.insert(entryDb);

            return new ReturnJsonObj(data);
        } catch (Exception e) {
            return new ReturnJsonObj("0", "请求失败", null);
        }

    }

    @PostMapping("/api/v3/tag")
    @ResponseBody
    public ReturnJsonObj searchTag(@RequestParam Map<String, Object> params) {
        try {
            String tag = params.get("name").toString();
            String pageNum = params.get("page_number").toString();
            String pageSize = params.get("page_size").toString();

//            Sort sort = new Sort(Sort.Direction.DESC, myTools.generateOrder());
            Pageable pageable = PageRequest.of(Integer.parseInt(pageNum), Integer.valueOf(pageSize));
            Page<EntryDb> entryDbs1 = iEntryServ.findByTags(tag, pageable);
            List<EntryDb> data = entryDbs1.getContent();

            return new ReturnJsonObj(data);
        } catch (Exception e) {
            return new ReturnJsonObj("0", "请求失败", null);
        }

    }

    @PostMapping("/api/v3/wordcloud")
    @ResponseBody
    public RecommendJsonObj wordCloud(@RequestParam Map<String, Object> params) {
        try {
            String tag = params.get("name").toString();

            //模糊匹配

            Pageable pageable = PageRequest.of(0, ONE_PAGE_NUM);
            Page<EntryDb> entryDbs1 = iEntryServ.wordcloud(tag, pageable);
            List<EntryDb> entryDbs = entryDbs1.getContent();

            Map<String, Integer> tagMap = new HashMap<>();
            for (int i = 0; i < entryDbs.size(); i++) {
                List<String> curTag = entryDbs.get(i).getTagList();
                for (int j = 0; j < curTag.size(); j++) {
                    String tagName = curTag.get(j);
                    if (tagMap.containsKey(curTag.get(j))) {
                        tagMap.put(tagName, tagMap.get(tagName) + (int) Math.round(Math.random() * 50));
                    } else {
                        tagMap.put(tagName, (int) Math.round(Math.random() * 50));
                    }
                }
            }
            List<RecommendObj> recommendObjs = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : tagMap.entrySet()) {
                recommendObjs.add(new RecommendObj(entry.getKey(), entry.getValue()));
            }

            return new RecommendJsonObj("0", "查询成功", recommendObjs);
        } catch (Exception e) {
            return new RecommendJsonObj("-1", "请求失败", null);
        }

    }


    @PostMapping("/api/v3/vote")
    @ResponseBody
    public ReturnJsonObj vote(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        try {

            Long id = Long.parseLong(params.get("id").toString());
            boolean isLike = Boolean.parseBoolean(params.get("like").toString());
            EntryDb entryDb = fatDbServ.findById(id);


            String ip = myTools.getIpAddr(request);
            if (ipServ.ipVote(ip, entryDb.getName())) {
                if (isLike) {
                    entryDb.setLike(entryDb.getLike() + 1);
                    fatDbServ.insert(entryDb);
                    entryDb.setVideoList(null);
                    entryDb.setImageList(null);
                    entryDb.setWeiboList(null);
                    iEntryServ.update(entryDb);
                } else {
                    entryDb.setDislike(entryDb.getDislike() + 1);
                    fatDbServ.insert(entryDb);
                    entryDb.setVideoList(null);
                    entryDb.setImageList(null);
                    entryDb.setWeiboList(null);
                    iEntryServ.update(entryDb);
                }
                List<EntryDb> entryDbs = Arrays.asList(entryDb);

                return new ReturnJsonObj(entryDbs);
            } else {
                List<EntryDb> entryDbs = Arrays.asList(entryDb);

                return new ReturnJsonObj("-1", "今天点赞/踩次数超过了50次，请明天再来", entryDbs);
            }


        } catch (Exception e) {
            return new ReturnJsonObj("0", "请求失败", null);
        }
    }

}
