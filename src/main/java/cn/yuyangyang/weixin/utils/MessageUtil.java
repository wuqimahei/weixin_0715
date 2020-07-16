package cn.yuyangyang.weixin.utils;

import cn.yuyangyang.weixin.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息格式转换
 */
public class MessageUtil {

    // 常量
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_NEWS = "news";

    /**
     * Event 包含关注和取关
     * 关注和取关的MsgType都是Event
     *
     * 再次通过标签Event判断是关注还是取关
     */
    public static final String MESSAGE_EVENT = "event";
    // 关注
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    // 取关
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    // click菜单
    public static final String MESSAGE_CLICK = "click";
    // view菜单
    public static final String MESSAGE_VIEW = "view";





    /**
     * XML2Map
     */
    public static Map<String, String> xml2Map(HttpServletRequest request) throws Exception {

        Map<String, String> map = new HashMap<>();

        SAXReader saxReader = new SAXReader();

        ServletInputStream inputStream = request.getInputStream();

        Document doc = saxReader.read(inputStream);
        Element element = doc.getRootElement();
        List<Element> elements = element.elements();

        for (Element e : elements) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }


    /**
     * 文本消息转为xml
     */
    public static String text2XML(TextMessage textMessage){
        XStream xStream = new XStream();
        // 将根结点的标签替换成xml
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }


    // 菜单
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎关注，回复序号");
        sb.append("1.女朋友");
        sb.append("2.男朋友");
        sb.append("？调出此菜单");
        return sb.toString();
    }


}
