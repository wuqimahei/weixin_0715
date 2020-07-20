package cn.yuyangyang.weixin.controller;


import cn.yuyangyang.weixin.utils.MessageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 被动回复的消息
 * 文本
 * 图片
 * 音频
 */

@RestController
public class PassiveReply {

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void textMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Map<String, String> map = MessageUtil.xml2Map(request);

            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String message = null;
            // 判断是不是文本消息
            if (MessageUtil.MESSAGE_TEXT.equals(msgType)){

                if ("1".equals(content)){
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.firstMenu());
                }else if ("2".equals(content)){
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                }else if ("?".equals(content) || "？".equals(content)){
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.menuText());
                }else if ("3".equals(content)){
                    message = MessageUtil.initImageMessage(toUserName, fromUserName);
                }
            }else if (MessageUtil.MESSAGE_EVENT.equals(msgType)){
                // MsgType 是 Event，判断是关注还是取关
                String event = map.get("Event");
                if (event.equals(MessageUtil.MESSAGE_SUBSCRIBE)){
                    // 关注
                    message = MessageUtil.initTextMessage(toUserName, fromUserName, MessageUtil.menuText());

                }else {
                    System.out.println(fromUserName + "取关了");
                }
            }
            out.print(message);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

}
