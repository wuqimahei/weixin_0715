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

//        String path = "/Users/yuyangyang/Downloads/1.jpg";
        String path = "D:/1.mp4";
        String mediaId = UploadFile.upload(path, accessToken.getAccess_token(), "video");
        System.out.println(mediaId);

    }
}
