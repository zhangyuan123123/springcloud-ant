package com.jk.controller;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Controller
@RequestMapping("ant")
public class AntClientController {


    @RequestMapping("xinwen")
    public String querynews(){
        return "xinwen";
    }

    @RequestMapping("wuZhuan")
    public String wuZhuan(String msg){
        if (msg.equals("1")){
            return"zhuanxian";
        }
        if (msg.equals("2")){
            return"gongsi";
        }
        if (msg.equals("3")){
            return"hangye";
        }
        if (msg.equals("4")){
            return"huoyun";
        }
        if (msg.equals("5")){
            return"mingren";
        }
        if (msg.equals("6")){
            return"zhaobiao";
        }
        if (msg.equals("7")){
            return"lvmayi";
        }
        if (msg.equals("8")){
            return"redian";
        }
        if (msg.equals("9")){
            return"wuliuzhuanxian";
        }
        return "xinwen";
    }

    @RequestMapping(value="queryduanxin",produces="application/json;charset=utf-8")
    @ResponseBody
    public String httpPost() {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //传参用的集合
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        //int newcode = (int) (Math.random() * 899999) + 100000;
        params.add(new BasicNameValuePair("top", "top"));
        params.add(new BasicNameValuePair("key", "5533627eb46b8bec2acb76919714f771"));//010129c37a935cfe33df53fcda585277


        //设置参数的编码格式
        UrlEncodedFormEntity Entity = null;
        try {
            Entity = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        //http://127.0.0.1:8081/compet-webClient/compet/findAll.do
        HttpPost httpPost = new HttpPost("http://v.juhe.cn/toutiao/index");

        httpPost.setEntity(Entity);

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1;"
                + " Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        httpPost.setHeader("Accept", "application/json");

        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(30000)        //设置链接超时的时间1秒钟
                .setSocketTimeout(30000)        //设置读取超时1秒钟
                .build();                        //RequestConfig静态方法  setProxy  设置代理


        httpPost.setConfig(config);

        CloseableHttpResponse response = null;
        String jsonStr = "";
        try {
            response = httpClient.execute(httpPost);
            jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8"); // json {}
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                response.close();
                httpPost.abort();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonStr;
    }
}
