package cn.yuyangyang.weixin.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.web.client.RestTemplate;

public class WeiXinUtil {

    private static final String APPID = "wx5d8941a97a377c3a";
    private static final String APPSECRET = "07b96cf7528cd2de6554ab7a0e6914a6";

    public static JSON doGetString(String url){

        // 发送Get请求
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(url, String.class);
        JSON jsonStr = null;
        return null;
    }

}
