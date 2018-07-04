package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：14:22 2018/6/18
 *
 * @说明：Dyunamic  动态的封装类
 */
public class Dyunamic {
    private String content;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String time;
    private String user;

    public String getContent() {
        return content;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public String getImg3() {
        return img3;
    }

    public String getImg4() {
        return img4;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Dyunamic{" +
                "content='" + content + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", img4='" + img4 + '\'' +
                ", time='" + time + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
