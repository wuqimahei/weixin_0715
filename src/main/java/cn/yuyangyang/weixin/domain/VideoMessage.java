package cn.yuyangyang.weixin.domain;

public class VideoMessage extends BaseMessage {
    private  Video Video;

    public cn.yuyangyang.weixin.domain.Video getVideo() {
        return Video;
    }

    public void setVideo(cn.yuyangyang.weixin.domain.Video video) {
        Video = video;
    }
}
