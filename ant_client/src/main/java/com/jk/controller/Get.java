package com.jk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jk.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.HashMap;

@Controller
public class Get {

    public static final String APPKEY = "45cf09d989b30bcb";// 你的appkey
    public static final String URL = "https://api.jisuapi.com/news/get";
    public static final String channel = "头条";// utf8  新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
    public static final int num = 10;// 数量 默认10，最大40


    @RequestMapping("xinwen")
    @ResponseBody
    public static HashMap<String, Object>  NewGet(Model model) throws Exception {
        String result = null;
        String url = URL + "?channel=" + URLEncoder.encode(channel, "utf-8") + "&num=" + num + "&appkey=" + APPKEY;
        HashMap<String, Object> hash = new HashMap<>();
        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONObject resultarr = (JSONObject) json.get("result");
                String channel = resultarr.getString("channel");
                String num = resultarr.getString("num");
                System.out.println(channel + " " + num);
                JSONArray list = resultarr.getJSONArray("list");

                for (int i = 0; i < list.size(); i++) {
                   JSONObject obj = (JSONObject) list.get(i);
                    String title = obj.getString("title");
                    String time = obj.getString("time");
                    String src = obj.getString("src");
                    String category = obj.getString("category");
                    String pic = obj.getString("pic");
                    String content = obj.getString("content");
                    String url1 = obj.getString("url");
                    String weburl = obj.getString("weburl");
                }
                hash.put("total",list.size());
                hash.put("rows",list);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return hash;



    }


}
