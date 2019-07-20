package com.example.cges.service;

import com.example.cges.mongoentity.UncheckedEntryDb;
import com.example.cges.repository.IMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoServ implements IMongoServ{

    private IMongoRepo mongoRepo;

    public MongoServ() {
    }
    @Autowired
    public MongoServ(IMongoRepo mongoRepo){
        this.mongoRepo = mongoRepo;
    }

    @Override
    public List<UncheckedEntryDb> findAll() {
        List<UncheckedEntryDb> uncheckedEntryDbs = new ArrayList<>();
        mongoRepo.findAll().forEach(uncheckedEntryDbs::add);
        return uncheckedEntryDbs;
    }

    @Override
    public UncheckedEntryDb saveEntry(UncheckedEntryDb uncheckedEntryDb) {
        return mongoRepo.save(uncheckedEntryDb);
    }

    @Override
    public UncheckedEntryDb findById(Long id) {
        return mongoRepo.findById(id);
    }

    @Override
    public List<UncheckedEntryDb> findByname(String name) {
        return mongoRepo.findByNameLike(name);
    }

    @Override
    public void deleteById(Long id) {
        mongoRepo.deleteById(id);
    }
}
