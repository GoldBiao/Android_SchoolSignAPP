package lewis.com.sign.base;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDexApplication;


public class DemoApplication extends MultiDexApplication {
    private static Application instance;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;


    }

    public static Context getAppContext() {
        return instance;
    }
}