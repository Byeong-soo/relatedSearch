package com.jobseeker.relatedSearch.service;

import java.util.List;
import java.util.Map;

public interface IWordAnalysisService {

    List<String> doWordNouns(String content) throws Exception;

    Map<String,Integer> doWordCount(List<String> wordList) throws Exception;

    Map<String,Integer> doWordAnalysis(String content) throws Exception;
}
