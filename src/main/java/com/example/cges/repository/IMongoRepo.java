package com.example.cges.repository;

import com.example.cges.mongoentity.UncheckedEntryDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IMongoRepo extends CrudRepository<UncheckedEntryDb, String> {
    public UncheckedEntryDb findById(Long id);
    public List<UncheckedEntryDb> findByNameLike(String name);
    public void deleteById(Long id);
}
