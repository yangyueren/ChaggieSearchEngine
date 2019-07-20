package com.example.cges.service;

import com.example.cges.mongoentity.UncheckedEntryDb;

import java.util.List;

public interface IMongoServ {
    UncheckedEntryDb saveEntry(UncheckedEntryDb uncheckedEntryDb);
    UncheckedEntryDb findById(Long id);
    List<UncheckedEntryDb> findByname(String name);
    List<UncheckedEntryDb> findAll();
    void deleteById(Long id);
}
