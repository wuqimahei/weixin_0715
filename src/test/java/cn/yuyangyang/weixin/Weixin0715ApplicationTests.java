package cn.yuyangyang.weixin;

import cn.yuyangyang.weixin.domain.AccessToken;
import cn.yuyangyang.weixin.utils.UploadFile;
import cn.yuyangyang.weixin.utils.WeiXinUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Weixin0715ApplicationTests {

    @Test
    public void weiXinTest() throws Exception {

        AccessToken accessToken = WeiXinUtil.getAccessToken();
        System.out.println("凭据:" + accessToken.getAccess_token());
        System.out.println("时间："+accessToken.getExpires_in());

        String path = "/Users/yuyangyang/Downloads/1.jpg";
//        String path = "/Users/yuyangyang/Downloads/2020.mp4";
//        String path = "/Users/yuyangyang/Downloads/2020.mp4";
        String mediaId = UploadFile.upload(path, accessToken.getAccess_token(), "thumb");
        System.out.println(mediaId);

    }


    /**
     * 测试菜单
     * @throws Exception
     */
    @Test
    public void weiXinMenuTest() throws Exception {

        AccessToken accessToken = WeiXinUtil.getAccessToken();
        System.out.println("凭据:" + accessToken.getAccess_token());
        System.out.println("时间："+accessToken.getExpires_in());

        String menu = JSONObject.toJSONString(WeiXinUtil.initMenu());
        int result = WeiXinUtil.createMenu(accessToken.getAccess_token(), menu);
        if (result == 0){
            System.out.println("菜单创建成功");
        }else {
            System.out.println("菜单创建失败，错误码："+result);
        }

    }
}
