package com.jobseeker.relatedSearch.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WordAnalysisServiceTest {

    @Autowired
    private WordAnalysisService wordAnalysisService;
    @Test
    void contentRepeatCount() throws Exception {
        //given
        String content = "안녕하세요 저는 취업 준비생 입니다. 한국글로벌널리지네트웍 과제 진행 중입니다.취업 화이팅";
        //when
        Map<String, Integer> stringIntegerMap = wordAnalysisService.doWordAnalysis(content);
        //then
        for (String s : stringIntegerMap.keySet()) {
            System.out.println("s = " + s);
        }
        assertEquals(stringIntegerMap.get("취업"),2);
        assertEquals(stringIntegerMap.get("진행"),1);
        assertEquals(stringIntegerMap.get("과제"),1);
    }
}