package cn.yuyangyang.weixin.utils;

import cn.yuyangyang.weixin.domain.AccessToken;
import cn.yuyangyang.weixin.menu.Button;
import cn.yuyangyang.weixin.menu.ClickButton;
import cn.yuyangyang.weixin.menu.Menu;
import cn.yuyangyang.weixin.menu.ViewButton;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

public class WeiXinUtil {
// 正式号
//    private static final String APPID = "wx5d8941a97a377c3a";
//    private static final String APPSECRET = "07b96cf7528cd2de6554ab7a0e6914a6";

    // 测试号
    private static final String APPID = "wx76b2740e7909fbf7";
    private static final String APPSECRET = "d1bf998fcdbfd9bdd5f7f5764323dc8b";

    // 主账号测试号
//    private static final String APPID = "wx3c6f40056e18a1df";
//    private static final String APPSECRET = "f0a4a1a94d7b43ad6f96abfbd98f62b0";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";


    /**
     * Get请求  可以用来获取access_token
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

    public static Menu initMenu(){
        Menu menu = new Menu();

        ClickButton clickButton1 = new ClickButton();
        clickButton1.setName("click菜单1");
        clickButton1.setType("click");
        clickButton1.setKey("1");

        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("扫码");
        clickButton2.setType("scancode_push");
        clickButton2.setKey("2");

        ClickButton clickButton3 = new ClickButton();
        clickButton3.setName("地理位置");
        clickButton3.setType("location_select");
        clickButton3.setKey("3");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("view菜单");
        viewButton.setType("view");
        viewButton.setUrl("http://blog.yuyangyang.cn");

        Button button = new Button();
        button.setName("菜单");
        // 二级菜单
        button.setSub_button(new Button[]{clickButton2, clickButton3});

        menu.setButton(new Button[]{clickButton1, viewButton, button});
        return menu;
    }

    public static int createMenu(String token, String menu){

        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);

        JSONObject jsonObject = doPostString(url, menu);

        if (jsonObject != null){
            // 正确时返回 {"errcode":0,"errmsg":"ok"}  通过errcode判断是否成功
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }



}
