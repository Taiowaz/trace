package com.lhl.trace.controller;

import com.lhl.trace.entity.api.GptApi;
import com.lhl.trace.entity.api.TransApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatGptController {

    @PostMapping("/chatgpt")
    public String chatGpt(@RequestParam("prompt") String prompt) throws Exception{
        //chatgpt不支持中文，需要进行翻译
        TransApi transApi = new TransApi();
        System.out.println("ChatController19-prompt:"+prompt);
        GptApi gptApi = new GptApi(transApi.getTransResult("auto","en",prompt));
        //获取英语回复
        String response = gptApi.getGptResult();
        //将结果翻译为中文
        String transResponse = transApi.getTransResult("auto","zh", response);
        return transResponse;
    }

    //!!!测试
    @PostMapping("/testTrans")
    public String trans(@RequestParam("prompt") String prompt) throws Exception {
        TransApi transApi = new TransApi();
        return transApi.getTransResult("auto","en",prompt);

    }

}
