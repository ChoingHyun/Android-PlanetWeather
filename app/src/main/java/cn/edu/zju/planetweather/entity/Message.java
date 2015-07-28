package cn.edu.zju.planetweather.entity;

/**
 * Created by changhuiyuan on 15/7/28.
 */
public class Message {

    private String publishTime;
    private String content;
    private String username;

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
