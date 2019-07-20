package com.example.cges.repository;

import com.example.cges.mongoentity.IPHistory;
import net.sf.json.JSONObject;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IIPRepo extends CrudRepository<IPHistory, String > {
    public List<IPHistory> findTop10ByIpAndVoteOrderByDateDesc(String ip, Integer vote);
    public List<IPHistory> findTop10ByIpOrderByDateDesc(String ip);

}
