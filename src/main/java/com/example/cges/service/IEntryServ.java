package com.example.cges.service;

import com.example.cges.entity.EntryDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

public interface IEntryServ {


    public List<EntryDb> findAll();
    //
    public EntryDb update(EntryDb entryDb);

    public void deleteById(Long id);

//    public EntryDb findById(Long id);

    public EntryDb insertNewEntry(EntryDb entryDb);
//

    public EntryDb insert(EntryDb entryDb);


    public boolean alreadyExistedEntryName(String entryName);
    public Page<EntryDb> findTop10ByView(String input, Pageable pageable);

    public Page<EntryDb> findByTags(String tag, Pageable pageable);


    public Page<EntryDb> wordcloud(String tag, Pageable pageable);

    public Page<EntryDb> recommend(String input, Pageable pageable);


    public Page<EntryDb> search(String key, String tag, Pageable pageable, String timeBegin, String timeEnd);

    public Page<EntryDb> search(String key, Pageable pageable, String timeBegin, String timeEnd);

    public Page<EntryDb> searchSentence(String content, Pageable pageable, String timeBegin, String timeEnd);
    public Page<EntryDb> searchQuoteMark(String quoteMark, String minusMark, String content, Pageable pageable, String timeBegin, String timeEnd);

}
