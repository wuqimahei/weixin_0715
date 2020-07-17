package cn.yuyangyang.weixin.domain;

import java.util.List;

/**
 * 图文消息外层部分
 */
public class NewsMessage extends BaseMessage{

    // 图文消息条数
    private Integer ArticleCount;
    // 消息体
    private List<News> Articles;

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}
