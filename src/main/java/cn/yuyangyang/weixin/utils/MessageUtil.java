package cn.yuyangyang.weixin.utils;

import cn.yuyangyang.weixin.domain.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

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

    /**
     * 图文消息转为xml
     */
    public static String news2XML(NewsMessage newsMessage){
        XStream xStream = new XStream();
        // 将根结点的标签替换成xml
        xStream.alias("xml", newsMessage.getClass());
        // 消息体使用的是item标签
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 拼装图文消息
     */
    public static String initNewsMessage(String toUserName, String fromUserName){

        String message = null;
        ArrayList<News> newsList = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();

        /**
         * 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
         * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=115383153198yAvN&version=&lang=zh_CN&token=
         */
        News news = new News();
        news.setTitle("blog4yuyangyang");
        news.setDescription("这是一个博客");
        news.setPicUrl("http://image.yuyangyang.cn/%E5%A4%87%E6%A1%88%E5%9B%BE%E6%A0%87.png");
        news.setUrl("http://blog.yuyangyang.cn");

        newsList.add(news);

        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.MESSAGE_NEWS);
        newsMessage.setArticleCount(newsList.size());
        newsMessage.setArticles(newsList);

        message = news2XML(newsMessage);
        return message;
    }


    /**
     * 拼接文本消息
     */
    public static String initTextMessage(String toUserName, String fromUserName, String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent("消息是：" + content);
        return MessageUtil.text2XML(textMessage);
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

    // 回复 1
    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("这是关键字1的内容");
        return sb.toString();
    }

    // 回复 2
    public static String secondMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("这是关键字2的内容");
        return sb.toString();
    }


    // 初始化图片消息
    public static String initImageMessage(String toUserName, String fromUserName){
        String message = null;
        Image image = new Image();
        // C3nbAnnV6VLTZF40xFs1ORKWZBk4ykjwYMgz_Y6szlBnO6Hw6DmfrAWVt0KJZu2l
        image.setMediaId("HxT4LAK34NXiEH6FoMUdRsNq96wC0venJl7kceHWnA_VkBnkt-54Rm74DeF0MVip");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message = image2XML(imageMessage);
        return message;
    }

    // 图片消息转xml
    public static String image2XML(ImageMessage imageMessage){
        XStream xStream = new XStream();
        // 将根结点的标签替换成xml
        xStream.alias("xml", imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }


    public static String initVideo(String toUserName, String fromUserName){

        String message = null;
        Video video = new Video();
        video.setTitle("视频标题");
        // Uxx7udVp8BD8NZTEGPnsKnt1aGAoDdnh_9rCiXFct17XmUqegTbbycdjIgtzRM0n
        video.setMediaId("Uxx7udVp8BD8NZTEGPnsKnt1aGAoDdnh_9rCiXFct17XmUqegTbbycdjIgtzRM0n");
        video.setDescription("视频描述");

        VideoMessage videoMessage = new VideoMessage();
        videoMessage.setFromUserName(toUserName);
        videoMessage.setToUserName(fromUserName);
        videoMessage.setCreateTime(new Date().getTime());
        videoMessage.setMsgType(MESSAGE_VIDEO);
        videoMessage.setVideo(video);
        message =  video2XML(videoMessage);
        System.out.println(message);
        return message;
    }

    public static String video2XML(VideoMessage videoMessage){
        XStream xStream = new XStream();
        // 将根结点的标签替换成xml
        xStream.alias("xml", videoMessage.getClass());
        return xStream.toXML(videoMessage);
    }




    public static String initMusic(String toUserName, String fromUserName){
        Music music = new Music();
        music.setTitle("这是音乐标题");
        music.setDescription("这是音乐描述");
        music.setMusicUrl("http://image.yuyangyang.cn/backgroundmusic.mp3");
        music.setHQMusicUrl("http://image.yuyangyang.cn/backgroundmusic.mp3");
        music.setThumbMediaId("p0MohHEeFcTObTYIdWJR-nfEYsw2T0idOfnmECDLYchRZhDeBdpTKETSXhORVGeb");

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setToUserName(fromUserName);
        musicMessage.setFromUserName(toUserName);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMsgType(MESSAGE_MUSIC);
        musicMessage.setMusic(music);
        return music2XML(musicMessage);
    }

    public static String music2XML(MusicMessage musicMessage){
        XStream xStream = new XStream();
        // 将根结点的标签替换成xml
        xStream.alias("xml", musicMessage.getClass());
        return xStream.toXML(musicMessage);
    }

}
