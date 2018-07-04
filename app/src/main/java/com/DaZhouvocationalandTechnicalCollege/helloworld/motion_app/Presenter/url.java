package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：20:47 2018/6/11
 *
 * @说明：url  Url接口类
 */





public class url {

    //http://bpic.588ku.com/video_mpeg/18/87/83/565a6e185a74a.mp4
    private static String login_mp4_url="http://bpic.588ku.com/video_listen/588ku_video/18/05/07/11/06/01/video5aefc299be8a3.mp4";
//    private static String login_mp4_url="http://bpic.588ku.com/video_listen/588ku_mpeg/17/12/24/5fe92cb48106494a8632d45ea416f55a.mp4";
    private static String login_url="http://39.105.37.77:8080/sports/login";//登录接口
    private  static String register_url="http://39.105.37.77:8080/sports/register";//注册接口
    private static String Bitmap_url="http://39.105.37.77:8080/upload/upload";//头像上传

    private static String CommunityFragment_top_iv_url="https://bpic.588ku.com/element_banner/20/18/06/80c88dd07b1ba5a490471cc828216a7f.jpg!/unsharp/true/compress/true";

    private static String historical_data_url="http://39.105.37.77:8080/sports/sevenData";//7天历史数据

    private static String all_historical_data_url="http://39.105.37.77:8080/sports/allData";//用户全部的历史数据

    private static String Change_nickname_url="http://39.105.37.77:8080/sports/updateName";//更改昵称

    private static String Change_password_url="http://39.105.37.77:8080/sports/updatePassword";//更改密码

    private static String Motion_data_uploading="http://39.105.37.77:8080/sports/motion/stepNumber";//上传每日运动的数据

    private static String ranking_url="http://39.105.37.77:8080/sports/ranking/all";//全部用户的排名

    private static String School_Rankings="http://39.105.37.77:8080/sports/ranking/school";//在学校排名

    private static String System_Rankings="http://39.105.37.77:8080/sports/ranking/college";//在院系的排名

    private static String Class_Rankings="http://39.105.37.77:8080/sports/ranking/class";//在班级排名

    private static String picture_upload="http://39.105.37.77:8080/sports/UploadHeadImg";//设置头像

    private static String get_picture="http://39.105.37.77:8080/sports/getHead";//获取头像

    private static String User_information_change="http://39.105.37.77:8080/sports/updateUser";//用户信息更改

    private static String User_data="http://39.105.37.77:8080/sports/queryUser";//用户所有基本信息的查询

    private static String publishVideo="http://39.105.37.77:8080/sports/publishVideo";//小视频上传接口

    private static String uploadDynamic="http://39.105.37.77:8080/sports/uploadDynamic";//用户发布动态的类

    private static String Dyunamic="http://39.105.37.77:8080/sports/getDynamicServlet";//动态获取

    private static String getVideo="http://39.105.37.77:8080/sports/getVideo";//小视频获取接口

    public static String getGetVideo() {
        return getVideo;
    }

    public static String getDyunamic() {
        return Dyunamic;
    }

    public static String getUploadDynamic() {
        return uploadDynamic;
    }

    public static String getUser_information_change() {
        return User_information_change;
    }

    public static String getUser_data() {
        return User_data;
    }

    private static String model_url="http://v.juhe.cn/sms/send";//短信验证接口

    public static String getModel_url() {
        return model_url;
    }

    public static String getPublishVideo() {
        return publishVideo;
    }

    /**
     * @时间 ：2018/6/14  9:18
     *
     * @说明 ：get
     *
     * id:要更改的用户id
    name:昵称
    sex:性别
    phone:电话号码
    email：邮箱
    height:身高
    weight:体重
    birthday:生日
    school：学校
    college：院系
    className:班级
     */


    private static String Information_change="http://39.105.37.77:8080/sports/updateUser";//用户信息更改




    public static String getHistorical_data_url() {
        return historical_data_url;
    }

    public static String getAll_historical_data_url() {
        return all_historical_data_url;
    }

    public static String getChange_nickname_url() {
        return Change_nickname_url;
    }

    public static String getChange_password_url() {
        return Change_password_url;
    }

    public static String getMotion_data_uploading() {
        return Motion_data_uploading;
    }

    public static String getRanking_url() {
        return ranking_url;
    }

    public static String getSchool_Rankings() {
        return School_Rankings;
    }

    public static String getSystem_Rankings() {
        return System_Rankings;
    }

    public static String getClass_Rankings() {
        return Class_Rankings;
    }

    public static String getPicture_upload() {
        return picture_upload;
    }

    public static String getGet_picture() {
        return get_picture;
    }

    public static String getInformation_change() {
        return Information_change;
    }









    public static String getCommunityFragment_top_iv_url() {
        return CommunityFragment_top_iv_url;
    }

    public static String getBitmap_url() {
        return Bitmap_url;
    }

    public static String getLogin_url() {
        return login_url;
    }

    public static String getRegister_url() {
        return register_url;
    }

    public static String getLogin_mp4_url() {
        return login_mp4_url;
    }
}
/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：9:34 2018/6/13
 *
 * @说明：url  登录	39.105.37.77:8080/sports/login     方式:post
参数
id:用户的id
password:用户密码

注册	39.105.37.77:8080/sports/register 	方式:post
参数	id:用户id
password:注册的密码
conPwd：确认密码

更改昵称   39.105.37.77:8080/sports/updateName	方式:POST
参数 	id:用户的id
password:注册的密码
name:用户的昵称

更改密码 39.105.37.77:8080/sports/updatePassword 方式:POST
参数	id:用户的id
usedPassword：旧密码
newPssword:新密码
conPwd:确认新密码


上传每日运动的数据	39.105.37.77:8080/sports/motion/stepNumber   方式:get
user:用户id
mileage:里程
stepNumber：步数
speed：速度

排名
全部用户的排名	39.105.37.77:8080/sports/ranking/all	方式:get
参数：无


在学校排名 39.105.37.77:8080/sports/ranking/school 方式：get
参数：
school：学校名字

在院系的排名 39.105.37.77:8080/sports/ranking/college 方式:get
参数	school：学校名字
college：院系名

在班级排名	39.105.37.77:8080/sports/ranking/class 方式：get
参数	school：学校名字
college：院系名
className :班级名称
 */