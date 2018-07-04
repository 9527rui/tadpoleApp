package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：20:48 2018/6/11
 *
 * @说明：user 用户基本数据类
 */

import android.graphics.Bitmap;

import java.util.List;

public class user {
    private static String id;//用户id
    private static String password;//用户密码
    private static String name;//用户昵称
    private static Bitmap user_Head_portrait;//用户头像
    private static String user_autograph;//个性签名
    private static String user_declaration;//运动宣言
    private static String[] user_Motion_record;//7天历史记录
    private static String[] user_data;//7天的时间
    private static String Sex;//性别
    private static String height;//身高
    private static String weight;//体重
    private static String School;//学校
    private static String Department;//系部
    private static String clazz;//班级
    private static String School_number;//学号
    private static String constellation;//星座
    private static String Birthday;//生日
    private static String Hometown;//故乡

    public static void setUser_data(String[] user_data) {
        user.user_data = user_data;
    }

    public static String[] getUser_data() {
        return user_data;
    }

    public static void setUser_autograph(String user_autograph) {
        user.user_autograph = user_autograph;
    }

    public static void setUser_declaration(String user_declaration) {
        user.user_declaration = user_declaration;
    }

    public static void setUser_Motion_record(String[] user_Motion_record) {
        user.user_Motion_record = user_Motion_record;
    }

    public static void setSex(String sex) {
        Sex = sex;
    }

    public static void setHeight(String height) {
        user.height = height;
    }

    public static void setWeight(String weight) {
        user.weight = weight;
    }

    public static void setSchool(String school) {
        School = school;
    }

    public static void setDepartment(String department) {
        Department = department;
    }

    public static void setClazz(String clazz) {
        user.clazz = clazz;
    }

    public static void setSchool_number(String school_number) {
        School_number = school_number;
    }

    public static void setConstellation(String constellation) {
        user.constellation = constellation;
    }

    public static void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public static void setHometown(String hometown) {
        Hometown = hometown;
    }

    public static String getUser_autograph() {
        return user_autograph;
    }

    public static String getUser_declaration() {
        return user_declaration;
    }

    public static String[] getUser_Motion_record() {
        return user_Motion_record;
    }

    public static String getSex() {
        return Sex;
    }

    public static String getHeight() {
        return height;
    }

    public static String getWeight() {
        return weight;
    }

    public static String getSchool() {
        return School;
    }

    public static String getDepartment() {
        return Department;
    }

    public static String getClazz() {
        return clazz;
    }

    public static String getSchool_number() {
        return School_number;
    }

    public static String getConstellation() {
        return constellation;
    }

    public static String getBirthday() {
        return Birthday;
    }

    public static String getHometown() {
        return Hometown;
    }

    public static void setUser_Head_portrait(Bitmap user_Head_portrait) {
        user.user_Head_portrait = user_Head_portrait;
    }

    public static Bitmap getUser_Head_portrait() {
        return user_Head_portrait;
    }

    public static void setName(String name) {
        user.name = name;
    }
    public static String getName() {

        return name;
    }


    //    private static int num;

//    public static void setNum(int num) {
//        user.num = num;
//    }
//
//    public static int getNum() {
//        return num;
//    }

    public static String getId() {
        return id;
    }

    public static String getPassword() {
        return password;
    }

    public static void setId(String id) {
        user.id = id;
    }

    public static void setPassword(String password) {
        user.password = password;
    }


}
