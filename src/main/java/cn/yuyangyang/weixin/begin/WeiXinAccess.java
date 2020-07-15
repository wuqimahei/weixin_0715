package cn.yuyangyang.weixin.begin;

import cn.yuyangyang.weixin.utils.CheckUtil;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信接入
 */
@RestController
public class WeiXinAccess {


    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     */
    @GetMapping("wx")
    public void wxAccess(String signature,
                           String timestamp,
                           String nonce,
                           String echostr,
                           HttpServletResponse response) throws IOException {
        System.out.println("===========");
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        System.out.println("===========");


        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println("进来了");
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.close();
        }
    }

}
