package com.jobseeker.relatedSearch.service;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordAnalysisService implements IWordAnalysisService{

    Komoran nlp = null;

    public WordAnalysisService() {
        this.nlp = new Komoran(DEFAULT_MODEL.FULL);
    }

    @Override
    public List<String> doWordNouns(String content) throws Exception {
        String replaceContent = content.replaceAll("[^가-힣a-zA-Z0-9]", " ");
        replaceContent = replaceContent.trim();
        KomoranResult analyzeResultList = this.nlp.analyze(replaceContent);
        List<String> rList = analyzeResultList.getNouns();
        if(rList == null){
            rList = new ArrayList<>();
        }
        return rList;
    }

    @Override
    public Map<String, Integer> doWordCount(List<String> wordList) throws Exception {

        if(wordList == null){
            wordList = new ArrayList<>();
        }

        Map<String,Integer> countMap = new HashMap<>();

        Set<String> wordSet = new HashSet<>(wordList);
        Iterator<String> wordIter = wordSet.iterator();

        while(wordIter.hasNext()){
            String word = wordIter.next();
            int count = Collections.frequency(wordList, word);
            countMap.put(word, count);
        }

        return countMap;
    }

    @Override
    public Map<String, Integer> doWordAnalysis(String content) throws Exception {

        List<String> wordList = this.doWordNouns(content);

        if(wordList == null){
            wordList = new ArrayList<>();
        }

        Map<String,Integer> countMap = this.doWordCount(wordList);

        if(countMap == null){
            countMap = new HashMap<>();
        }
        return countMap;
    }
}
