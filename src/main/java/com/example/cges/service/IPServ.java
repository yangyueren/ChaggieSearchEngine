package com.example.cges.service;

import com.example.cges.mongoentity.IPHistory;
import com.example.cges.repository.IIPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IPServ {
    private IIPRepo iipRepo;
    private final Long DAY = 86400000L;
    private final Integer VOTE = 1;
    private final Integer INSERT = 2;
    private final Integer VISIT = 3;
    private final Integer VOTE_ONE_DAY = 50;
    @Autowired
    public IPServ(IIPRepo iipRepo) {
        this.iipRepo = iipRepo;
    }
    public List<IPHistory> findAll(){
        List<IPHistory> ipHistories = new ArrayList<>();
        iipRepo.findAll().forEach(ipHistories :: add);
        return ipHistories;
    }

    public boolean ipVote(String ip, String name){
        List<IPHistory> ipHistories = iipRepo.findTop10ByIpAndVoteOrderByDateDesc(ip, VOTE);

        if (ipHistories.size() > VOTE_ONE_DAY){
            Integer begin = 0;
            Integer end = VOTE_ONE_DAY;
            Long days = (ipHistories.get(end).getDate().getTime() - ipHistories.get(begin).getDate().getTime()) / DAY;
            if (days > 1){
                IPHistory ipHistory = new IPHistory(ip, name, VOTE);
                iipRepo.save(ipHistory);
            }else {
                return false;
            }
        }
        else {
            IPHistory ipHistory = new IPHistory(ip, name, VOTE);
            iipRepo.save(ipHistory);
        }
        return true;
    }
    public void ipVisit(String ip, String name, Integer mode){
        IPHistory ipHistory = new IPHistory(ip, name, mode);
        iipRepo.save(ipHistory);
    }
    public List<String> ipRecommend(String ip){
        List<IPHistory> ipHistories = iipRepo.findTop10ByIpOrderByDateDesc(ip);
//        System.out.println(ipHistories.toString());
        List<String> names = new ArrayList<>();
        for (int i = 0; i < ipHistories.size(); i++) {
//            System.out.println(ipHistories.get(i).getDate().toString());
            names.add(ipHistories.get(i).getName());
        }
        return names;
    }

    public Integer getVOTE() {
        return VOTE;
    }

    public Integer getINSERT() {
        return INSERT;
    }

    public Integer getVISIT() {
        return VISIT;
    }
}
