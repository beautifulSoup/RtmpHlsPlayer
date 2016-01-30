package cn.campusapp.rtmpplayer;

import android.app.Application;
import android.content.Context;

/**
 * Created by kris on 16/1/27.
 */
public class App extends Application {

    private static App APPLICATION;

    public static Context getContext(){
        return APPLICATION;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION = this;
    }
}
