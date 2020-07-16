package cn.yuyangyang.weixin.controller;

import cn.yuyangyang.weixin.domain.TextMessage;
import cn.yuyangyang.weixin.utils.MessageUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * 被动回复的消息
 * 文本
 * 图片
 * 音频
 */

@RestController
public class Message {

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
                TextMessage textMessage = new TextMessage();
                textMessage.setFromUserName(toUserName);
                textMessage.setToUserName(fromUserName);
                textMessage.setMsgType("text");
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setContent("消息是：" + content);
                message = MessageUtil.text2XML(textMessage);
            }else if (MessageUtil.MESSAGE_EVENT.equals(msgType)){
                // MsgType 是 Event，判断是关注还是取关了
                String event = map.get("Event");
                if (event.equals(MessageUtil.MESSAGE_SUBSCRIBE)){
                    // 关注
                }else {

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
