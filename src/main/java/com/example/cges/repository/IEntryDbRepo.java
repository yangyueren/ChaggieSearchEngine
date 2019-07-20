package com.example.cges.repository;

import com.example.cges.entity.EntryDb;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;



@Repository
public interface IEntryDbRepo extends ElasticsearchRepository<EntryDb, Long> {
    public List<EntryDb> findByName(String entryName);
    public List<EntryDb> findByNameLike(String entryName);
    public List<EntryDb> findByContentLike(String content);


    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": {\n" +
            "            \"match\": {\n" +
            "                \"tagList\": \"?0\"\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "    }")
    public Page<EntryDb> findTag(String tag, Pageable pageable);

    @Query("{ \"match_all\": {} }")
    public Page<EntryDb> findTop10Views(String input, Pageable pageable);

    @Query("{\n" +
            "\"multi_match\": {\n" +
            "    \"query\": \"?0\",\n" +
            "    \"type\": \"best_fields\",\n" +
            "    \"fields\": [\n" +
            "        \"name^10\",\n" +
            "        \"tags^2\",\n" +
            "        \"content\"\n" +
            "    ],\n" +
            "    \"tie_breaker\": 0.5,\n" +
            "    \"minimum_should_match\": \"50%\"\n" +
            "}\n" +
            "}")
    public Page<EntryDb> recommend(String input, Pageable pageable);


    @Query("{\n" +
            "    \n" +
            "    \"multi_match\": {\n" +
            "        \"query\":                \"?0\",\n" +
            "        \"type\":                 \"best_fields\", \n" +
            "        \"fields\":               [ \"name^6\",\"tagList^1.5\",\"content^4\" ],\n" +
            "        \"tie_breaker\":          0.3,\n" +
            "        \"minimum_should_match\": \"30%\" \n" +
            "    }\n" +
            "\n" +
            "  }")
    public Page<EntryDb> wordcloud(String key, Pageable pageable);
//

    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": {\n" +
            "            \"match\": {\n" +
            "                \"tagList\": \"?1\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"should\": {\n" +
            "            \"multi_match\": {\n" +
            "                \"query\": \"?0\",\n" +
            "                \"type\": \"best_fields\",\n" +
            "                \"fields\": [\n" +
            "                    \"name^5\",\n" +
            "                    \"content\"\n" +
            "                ],\n" +
            "                \"tie_breaker\": 0.4,\n" +
            "                \"minimum_should_match\": \"40%\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"filter\": {\n" +
            "            \"range\": {\n" +
            "                \"time\": {\n" +
            "                    \"gte\": \"?2\",\n" +
            "                    \"lt\": \"?3\"\n" +
            "                }\n" +
            "            }\n" +
            "          }\n" +
            "    }\n" +
            "    \n" +
            "}")
    public Page<EntryDb> searchWithTag(String key, String tag, String timeBegin, String timeEnd,Pageable pageable);


    @Query(" {\n" +
            "\"bool\": {\n" +
            "    \"should\": {\n" +
            "        \"multi_match\": {\n" +
            "            \"query\": \"?0\",\n" +
            "            \"type\": \"best_fields\",\n" +
            "            \"fields\": [\n" +
            "                \"name^8\",\n" +
            "                \"tagList^2\",\n" +
            "                \"content\"\n" +
            "            ],\n" +
            "            \"tie_breaker\": 0.4,\n" +
            "            \"minimum_should_match\": \"40%\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"filter\": {\n" +
            "        \"range\": {\n" +
            "            \"time\": {\n" +
            "                \"gte\": \"?1\",\n" +
            "                \"lt\": \"?2\"\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "}")
    public Page<EntryDb> search(String key, String timeBegin, String timeEnd, Pageable pageable);


    @Query("{\n" +
            "        \"bool\": {\n" +
            "            \"must\": {\n" +
            "                \"bool\":{ \n" +
            "                    \"should\": \n" +
            "                        [\n" +
            "                            {\"match\": {\n" +
            "                                \"name\": \"?0\"\n" +
            "                            }},\n" +
            "                            {\"match\": {\n" +
            "                                \"tagList\": \"?0\"\n" +
            "                            }}\n" +
            "                        ],\n" +
            "                    \"minimum_should_match\": \"1\",\n" +
            "                    \"must\": {\n" +
            "                        \"multi_match\": {\n" +
            "                            \"query\": \"?2\",\n" +
            "                            \"type\": \"best_fields\",\n" +
            "                            \"fields\": [\n" +
            "                                \"name^5\",\n" +
            "                                \"content\"\n" +
            "                            ],\n" +
            "                            \"tie_breaker\": 0.4,\n" +
            "                            \"minimum_should_match\": \"1\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "                    \n" +
            "             \n" +
            "            },\n" +
            "            \"must_not\": \n" +
            "                [\n" +
            "                    {\n" +
            "                        \"match\": {\n" +
            "                            \"name\": \"?1\"\n" +
            "                        }\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"match\": {\n" +
            "                            \"tagList\": \"?1\" \n" +
            "                        }\n" +
            "                    }\n" +
            "            ],\n" +
            "            \n" +
            "            \"filter\": {\n" +
            "                \"range\": {\n" +
            "                    \"time\": {\n" +
            "                        \"gte\": \"?3\",\n" +
            "                        \"lt\": \"?4\"\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }   \n" +
            "    }")
    public Page<EntryDb> searchWithQuoteMark(String quoteMark, String minusMark, String content, String timeBegin, String timeEnd, Pageable pageable);


    @Query("{\n" +
            "\"bool\": {\n" +
            "    \"must\": {\n" +
            "        \"match\": {\n" +
            "            \"content\": \"?0\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"should\": {\n" +
            "        \"multi_match\": {\n" +
            "            \"query\": \"?0\",\n" +
            "            \"type\": \"best_fields\",\n" +
            "            \"fields\": [\n" +
            "                \"name^2\",\n" +
            "                \"content^8\"\n" +
            "            ],\n" +
            "            \"tie_breaker\": 0.4,\n" +
            "            \"minimum_should_match\": \"40%\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"filter\": {\n" +
            "        \"range\": {\n" +
            "            \"time\": {\n" +
            "                \"gte\": \"?1\",\n" +
            "                \"lt\": \"?2\"\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "}")
    public Page<EntryDb> searchSentence(String sentence, String timeBegin, String timeEnd, Pageable pageable);

}
