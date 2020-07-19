package cn.yuyangyang.weixin.utils;

import cn.yuyangyang.weixin.domain.AccessToken;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

public class WeiXinUtil {
// 正式号
//    private static final String APPID = "wx5d8941a97a377c3a";
//    private static final String APPSECRET = "07b96cf7528cd2de6554ab7a0e6914a6";

    // 测试号
    private static final String APPID = "wx76b2740e7909fbf7";
    private static final String APPSECRET = "d1bf998fcdbfd9bdd5f7f5764323dc8b";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * Get 获取access_token
     */
    public static JSONObject doGetString(String url){

        // 发送Get请求
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(url, String.class);
        System.out.println("response:" + response);
        JSONObject jsonStr = null;

        if (response != null){
            jsonStr =  JSONObject.parseObject(response);
        }
        System.out.println("jsonStr:" + jsonStr);
        return jsonStr;
    }


    public static JSONObject doPostString(String url, String outStr){
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.postForObject(url, outStr, String.class);

        JSONObject jsonStr = JSONObject.parseObject(response);;

        return jsonStr;
    }


    /**
     * 获取access_token
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace( "APPSECRET", APPSECRET);
        System.out.println(url);
        JSONObject token = doGetString(url);
        System.out.println(token);
        if (token != null){
            accessToken.setAccess_token(token.getString("access_token"));
            accessToken.setExpires_in(token.getString("expires_in"));
        }
        return accessToken;
    }

}
