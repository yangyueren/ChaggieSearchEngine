package com.example.cges.myutils;


import com.example.cges.entity.EntryDb;
import com.example.cges.service.FatDbServ;
import com.example.cges.service.IEntryServ;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MyTools {
    public static final Integer ENTRY_ID_BEGIN = 10000;
    public Integer now = 0;
    private static byte[] lock = new byte[0];

    private IEntryServ iEntryServ;
    private FatDbServ fatDbServ;

    public MyTools(IEntryServ iEntryServ, FatDbServ fatDbServ) {
        this.iEntryServ = iEntryServ;
        this.fatDbServ = fatDbServ;
    }

    public MyTools() {
    }

    public List<EntryDb> sortData(List<EntryDb> data, List<String> orderBys) {

        String mode = "";
        if (orderBys.isEmpty()) {
            return data;
        } else {
            for (int i = 0; i < orderBys.size(); i++) {
                mode = mode + orderBys.get(i);
            }
        }

        return listAllSrot(data, mode);
    }

    private List<EntryDb> listAllSrot(List<EntryDb> data, String mode) {
        List<EntryDb> modifyData = new ArrayList<>(data);
        switch (mode) {
            case "timelikeview":
                Collections.sort(modifyData, (a, b) -> {

                    if (!a.getTime().equals(b.getTime())) {
                        return compareTime(b.getTime(),a.getTime());
                    } else {
                        if (!a.getLike().equals(b.getLike())) {
                            return b.getLike() - a.getLike();
                        } else {
                            if (!a.getView().equals(b.getView())) {
                                return b.getView() - a.getView();
                            }
                        }
                    }
                    return 0;
                });
                break;
            case "timeviewlike":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getTime().equals(b.getTime())) {
                        return compareTime(b.getTime(),a.getTime());
                    } else {
                        if (!a.getView().equals(b.getView())) {
                            return b.getView() - a.getView();
                        } else {
                            if (!a.getLike().equals(b.getLike())) {
                                return b.getLike() - a.getLike();
                            }
                        }
                    }
                    return 0;
                });
                break;
            case "viewliketime":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getView().equals(b.getView())) {
                        return b.getView() - a.getView();
                    } else {
                        if (!a.getLike().equals(b.getLike())) {
                            return b.getLike() - a.getLike();
                        } else {
                            if (!a.getTime().equals(b.getTime())) {
                                return compareTime(b.getTime(),a.getTime());
                            }
                        }
                    }
                    return 0;
                });
                break;
            case "viewtimelike":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getView().equals(b.getView())) {
                        return b.getView() - a.getView();
                    } else {
                        if (!a.getTime().equals(b.getTime())) {
                            return compareTime(b.getTime(),a.getTime());
                        } else {
                            if (!a.getLike().equals(b.getLike())) {
                                return b.getLike() - a.getLike();
                            }
                        }

                    }
                    return 0;
                });
                break;
            case "liketimeview":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getLike().equals(b.getLike())) {
                        return b.getLike() - a.getLike();
                    } else {
                        if (!a.getTime().equals(b.getTime())) {
                            return compareTime(b.getTime(),a.getTime());
                        } else {
                            if (!a.getView().equals(b.getView())) {
                                return b.getView() - a.getView();
                            }
                        }
                    }

                    return 0;
                });
                break;
            case "likeviewtime":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getLike().equals(b.getLike())) {
                        return b.getLike() - a.getLike();
                    } else {
                        if (!a.getView().equals(b.getView())) {
                            return b.getView() - a.getView();
                        } else {
                            if (!a.getTime().equals(b.getTime())) {
                                return compareTime(b.getTime(),a.getTime());
                            }
                        }
                    }
                    return 0;
                });
                break;
            case "likeview":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getLike().equals(b.getLike())) {
                        return b.getLike() - a.getLike();
                    } else {
                        if (!a.getView().equals(b.getView())) {
                            return b.getView() - a.getView();
                        }
                    }
                    return 0;
                });
                break;
            case "liketime":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getLike().equals(b.getLike())) {
                        return b.getLike() - a.getLike();
                    } else {
                        if (!a.getTime().equals(b.getTime())) {
                            return compareTime(b.getTime(),a.getTime());
                        }
                    }
                    return 0;
                });
                break;
            case "timelike":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getTime().equals(b.getTime())) {
                        return compareTime(b.getTime(),a.getTime());
                    } else {
                        if (!a.getLike().equals(b.getLike())) {
                            return b.getLike() - a.getLike();
                        }
                    }
                    return 0;
                });
                break;
            case "timeview":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getTime().equals(b.getTime())) {
                        return compareTime(b.getTime(),a.getTime());
                    } else {

                        if (!a.getView().equals(b.getView())) {
                            return b.getView() - a.getView();
                        }
                    }
                    return 0;
                });
                break;
            case "viewtime":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getView().equals(b.getView())) {
                        return b.getView() - a.getView();
                    } else {
                        if (!a.getTime().equals(b.getTime())) {
                            return compareTime(b.getTime(),a.getTime());
                        }
                    }

                    return 0;
                });
                break;
            case "viewlike":
                Collections.sort(modifyData, (a, b) -> {
                    if (!a.getView().equals(b.getView())) {
                        return b.getView() - a.getView();
                    } else {
                        if (!a.getLike().equals(b.getLike())) {
                            return b.getLike() - a.getLike();
                        }
                    }
                    return 0;
                });
                break;
            case "view":
                Collections.sort(modifyData, (a, b) -> {

                    if (!a.getView().equals(b.getView())) {
                        return b.getView() - a.getView();
                    }
                    return 0;
                });
                break;
            case "time":
                Collections.sort(modifyData, (a, b) -> {

                    if (!a.getTime().equals(b.getTime())) {
                        return compareTime(b.getTime(),a.getTime());
                    }
                    return 0;
                });
                break;
            case "like":
                Collections.sort(modifyData, (a, b) -> {

                    if (!a.getLike().equals(b.getLike())) {
                        return b.getLike() - a.getLike();
                    }
                    return 0;
                });
                break;
            default:break;
        }

        return modifyData;
    }

//    public static void main(String[] args){
//        MyTools myTools = new MyTools();
//        System.out.println(myTools.compareTime("2298-09-09","2009-09-09"));
//    }
    public int compareTime(String a, String b){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date aa = sdf.parse(a);
            Date bb = sdf.parse(b);
            if (aa.after(bb)){
                return 1;
            }else {
                return -1;
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }

    }

    public void fireInHole() {
        String sepatator = File.separator;
        List<EntryDb> entryDbs = new ArrayList<>();
        File directory = new File(System.getProperty("user.dir") + "/merged");//设定为当前文件夹
        try {
            File[] tempList = directory.listFiles();

            for (int i = 0; i < tempList.length; i++) {
                this.insertAllData(tempList[i].getAbsolutePath());
            }
        } catch (Exception e) {
        }

    }

    public void insertAllData(String path) {
        String content = "";
        try {
            File file = new File(path);
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
                content += "\n";
            }
            reader.close();

            File toFile = new File(System.getProperty("user.dir") + "/inserted/" + file.getName());


            if (this.insertDataToES(content)) {
                file.renameTo(toFile);
            }


        } catch (Exception e) {
            System.out.println("error: Insert All Data (code line69)............");
        }

    }

    public boolean insertDataToES(String content) {
        try {
            Gson gson = new Gson();
            EntryDb entryDb = new EntryDb();
            entryDb = gson.fromJson(content, EntryDb.class);
            if (now % 10 == 0)
                System.out.println("Current entry: " + String.valueOf(now) + "\n\n");
            fatDbServ.insert(entryDb);
            entryDb.setWeiboList(null);
            entryDb.setImageList(null);
            entryDb.setVideoList(null);
            iEntryServ.insert(entryDb);
            now++;
            return true;
        } catch (Exception e) {
            System.out.println(content.substring(0, 20));


        }
        return false;
    }

    public Long generateUniqueId() {

        // 位数，默认是8位
        Long w = 100000000L;
        long r = 0;
        int a = 0;
        int b = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
            a = (int) (Math.random() * 5);
            b = (int) (Math.random() * 5) + a;
        }

        String id = System.currentTimeMillis() + String.valueOf(r);
        int length = id.length();
        id = id.substring(0, a) + id.substring(b, length);
        if (id.length() > 8) {
            id = id.substring(0, 8);
        }

        return Long.parseLong(id);


    }

    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    public List<EntryDb> slimTheReturnJsonObj(List<EntryDb> entryDbs) {
        for (int i = 0; i < entryDbs.size(); i++) {
            entryDbs.get(i).setVideoList(null);
            entryDbs.get(i).setWeiboList(null);
            entryDbs.get(i).setImageList(null);
        }
        return entryDbs;
    }

    public List<EntryDb> slimestReturnJsonObj(List<EntryDb> entryDbs) {
        for (int i = 0; i < entryDbs.size(); i++) {
            entryDbs.get(i).setVideoList(null);
            entryDbs.get(i).setWeiboList(null);
            entryDbs.get(i).setImageList(null);
            entryDbs.get(i).setContent("");
            entryDbs.get(i).setTagList(null);
        }
        return entryDbs;
    }

}
