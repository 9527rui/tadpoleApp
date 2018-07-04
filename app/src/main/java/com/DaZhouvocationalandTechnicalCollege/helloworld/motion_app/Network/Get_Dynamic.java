package com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Network;

import com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.mGson.Dyunamic;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.DaZhouvocationalandTechnicalCollege.helloworld.motion_app.Presenter.url.getDyunamic;

/**
 * @作者：yangshijie
 * @QQ:
 * @时间：14:21 2018/6/18
 * @说明：Get_Dynamic 获得动态的类
 */
public class Get_Dynamic {

    private Dyunamic[] dyunamics;


    public Dyunamic[] Get_Dynamic(int i) {
        final int s = i;

        Okhttp_utils utils = new Okhttp_utils ();
        utils.seturl (getDyunamic () + "?page=" + s);
        utils.setMode ("get");
        try {
            final String data = utils.getData ();
            if (data.equals ("[]")) {
                return null;
            }
            Gson gson = new Gson ();
            dyunamics = gson.fromJson (data, Dyunamic[].class);


        } catch (IOException e) {
            e.printStackTrace ();
        }

        return dyunamics;
    }


}
