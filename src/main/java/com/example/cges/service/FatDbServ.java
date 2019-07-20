package com.example.cges.service;

import com.example.cges.entity.EntryDb;
import com.example.cges.entity.EntryFatDb;
import com.example.cges.repository.IFatDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FatDbServ {
    private  IFatDbRepo iFatDbRepo;

    @Autowired
    public FatDbServ(IFatDbRepo repository){
        this.iFatDbRepo = repository;
    }
    public EntryDb findById(Long id){
        EntryFatDb entryFatDb = iFatDbRepo.findById(id).get();
        return new EntryDb(entryFatDb);
    }
    public EntryDb insert(EntryDb entryDb){
        EntryFatDb entryFatDb = new EntryFatDb(entryDb);
        iFatDbRepo.save(entryFatDb);
        return new EntryDb(entryFatDb);
    }


}
