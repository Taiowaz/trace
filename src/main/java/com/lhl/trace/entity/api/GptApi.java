package com.lhl.trace.entity.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GptApi {
    private static final String GPT_API_HOST = "https://chat.canoe0.top/v1/chat/completions";
    private static final String API_KEY = "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";
    private static final float temperature = 0.9F;
    //生成文本的长度
    private static final int maxTokens = 4096;
    //模型类型
    private static final String model = "gpt-3.5-turbo";

    private String prompt;

    public GptApi(String prompt) {
        this.prompt = prompt;
    }


    //！！新版备用方案
    public String getGptResult() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        //定义post请求
        HttpPost request = new HttpPost(GPT_API_HOST);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + API_KEY);

        //定义一个JSON对象将参数放入对象中
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        JSONObject msgJson = new JSONObject();
        msgJson.put("role", "user");
        msgJson.put("content", prompt);
        List<JSONObject> msgList = new ArrayList<>();
        msgList.add(msgJson);
        requestBody.put("messages", msgList);
        //定义请求体
        StringEntity requestEntity = new StringEntity(requestBody.toString());
        //设置请求体
        request.setEntity(requestEntity);
        //发送请求
        HttpResponse response = httpClient.execute(request);
        //对回复进行整理
        String responseString = EntityUtils.toString(response.getEntity());
        JSONObject responseJson = JSONObject.parseObject(responseString);
        System.out.println(responseJson);
        List<JSONObject> choicesList = responseJson.getJSONArray("choices");
        JSONObject choicesJson = choicesList.get(0);
        JSONObject resMsgJson = choicesJson.getJSONObject("message");
        String resContent = resMsgJson.getString("content");
        return resContent;
    }

}
