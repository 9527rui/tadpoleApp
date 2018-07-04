package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.utils;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network.Okhttp_utils;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Dyunamic;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.GsonVideou;
import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Name;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getUser_data;

/**
 * @作者：yangshijie
 *
 * @QQ:
 *
 * @时间：22:58 2018/6/18
 *
 * @说明：getHeadurl   获取用户昵称的类
 */
public class getUserurl {
    private Name[] names;
    private Name name;

    public Name[] getUserurl(List<Dyunamic> list){
        names=new Name[5];

        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        for (int a=0;a<list.size ();a++){
           utils.seturl (getUser_data()+"?id="+list.get (a).getUser ());
            try {
                final String doing = utils.doing ();
                if (doing==null){
                    return null;
                }
                Gson gson=new Gson ();

                final Name[] names1 = gson.fromJson (doing, Name[].class);
                names[a]=new Name ();
                names[a].setName (names1[0].getName ());


            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        return names;
    }
    public Name[] getUserurl(List<Dyunamic> list,int c){
        names=new Name[c];

        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        for (int a=0;a<list.size ();a++){
            utils.seturl (getUser_data()+"?id="+list.get (a).getUser ());
            try {
                final String doing = utils.doing ();
                if (doing==null){
                    return null;
                }
                Gson gson=new Gson ();

                final Name[] names1 = gson.fromJson (doing, Name[].class);
                names[a]=new Name ();
                names[a].setName (names1[0].getName ());


            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        return names;
    }

    public Name[] getUserurl(GsonVideou[] list, int c){
        names=new Name[c];

        Okhttp_utils utils=new Okhttp_utils ();
        utils.setMode ("get");
        for (int a=0;a<list.length;a++){
            utils.seturl (getUser_data()+"?id="+list[a].getUser ());
            try {
                final String doing = utils.doing ();
                if (doing==null){
                    return null;
                }
                Gson gson=new Gson ();

                final Name[] names1 = gson.fromJson (doing, Name[].class);
                names[a]=new Name ();
                names[a].setName (names1[0].getName ());


            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        return names;
    }


}
