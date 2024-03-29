package com.lhl.trace.entity.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostRequest {
    private String apiHost;
    private String appid;
    private String apiKey;

    public PostRequest(String apiHost, String appid, String apiKey) {
        this.apiHost = apiHost;
        this.appid = appid;
        this.apiKey = apiKey;
    }

    public Map sendPostRequest(String requestBody) throws Exception {
        //得到url
        URL url = new URL(apiHost);
        //得到网络访问对象
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求参数
        connection.setRequestMethod("POST");//请求方式
        connection.setDoOutput(true);//是否向connection输出，默认false
        connection.setDoInput(true);//是否从connection读入，默认true

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        //构建请求体
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(requestBody);
        outputStream.flush();
        outputStream.close();

        //读取回复
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = inputReader.readLine()) != null) {
            responseBuilder.append(line);
        }
        inputReader.close();
        //return responseBuilder.toString();

        HashMap response = new HashMap<>();
        response.put("status", connection.getResponseCode());
        response.put("msg", connection.getResponseMessage());
        response.put("data", responseBuilder.toString());
        return response;
    }

}
