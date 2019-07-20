package com.example.cges.service;

import com.example.cges.entity.EntryDb;
import com.example.cges.repository.IEntryDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServ implements IEntryServ{
    private final IEntryDbRepo repository;

    @Autowired
    public EntryServ(IEntryDbRepo repository){
        this.repository = repository;
    }

    @Override
    public Page<EntryDb> wordcloud(String input, Pageable pageable) {
        return repository.wordcloud(input, pageable);
    }

    @Override
    public Page<EntryDb> search(String key, String tag, Pageable pageable, String timeBegin, String timeEnd) {
        return repository.searchWithTag(key, tag, timeBegin, timeEnd, pageable);
    }

    @Override
    public Page<EntryDb> search(String key, Pageable pageable, String timeBegin, String timeEnd) {
//        System.out.println(key+timeBegin+pageable.getPageNumber()+pageable.getPageSize());
        return repository.search(key, timeBegin, timeEnd, pageable);
    }



    @Override
    public Page<EntryDb> searchSentence(String sentence, Pageable pageable, String timeBegin, String timeEnd) {
        return repository.searchSentence(sentence, timeBegin, timeEnd, pageable);
    }

    @Override
    public Page<EntryDb> searchQuoteMark(String quoteMark, String minusMark, String content, Pageable pageable, String timeBegin, String timeEnd) {
        return repository.searchWithQuoteMark(quoteMark, minusMark, content, timeBegin, timeEnd, pageable);
    }

    public Page<EntryDb> recommend(String input, Pageable pageable) {
        return repository.recommend(input, pageable);
    }


    public List<EntryDb> findAll(){
        List<EntryDb> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
//
    public EntryDb update(EntryDb entryDb) {
        return repository.save(entryDb);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    
//    public EntryDb findById(Long id) {
//        return repository.findById(id).get();
//    }
//
    
    public EntryDb insertNewEntry(EntryDb entryDb) {
        if(!alreadyExistedEntryName(entryDb.getName())){
            return repository.save(entryDb);
        }else {
            return repository.findByName(entryDb.getName()).get(0);
        }
    }
//
    
    public EntryDb insert(EntryDb entryDb) {
        return repository.save(entryDb);
    }

    
    public boolean alreadyExistedEntryName(String entryName) {
        List<EntryDb> a = repository.findByName(entryName);
        for (int i = 0; i < a.size(); i++) {
        }
        return repository.findByName(entryName).size() != 0;
    }
    public Page<EntryDb> findTop10ByView(String input, Pageable pageable){
        return repository.findTop10Views(input, pageable);
    }
//
    public Page<EntryDb> findByTags(String tag, Pageable pageable) {
        return repository.findTag(tag, pageable);
    }


}
