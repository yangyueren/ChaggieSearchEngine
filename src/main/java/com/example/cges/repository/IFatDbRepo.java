package com.example.cges.repository;


import com.example.cges.entity.EntryDb;
import com.example.cges.entity.EntryFatDb;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFatDbRepo extends ElasticsearchRepository<EntryFatDb, Long> {

}
