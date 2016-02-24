package wx.ry.org.wxhelper.app;

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by renyang on 16/2/16.
 */
public class App extends Application{

    public static int UI_SCREEN_WIDTH;
    public static int UI_SCREEN_HEIGHT;
    public static float UI_DENSITY;
    public static int UI_DENSITYDPI;

    @Override
    public void onCreate() {
        super.onCreate();

        final DisplayMetrics m = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            UI_SCREEN_WIDTH = m.heightPixels;
            UI_SCREEN_HEIGHT = m.widthPixels;
        } else {
            UI_SCREEN_WIDTH = m.widthPixels;
            UI_SCREEN_HEIGHT = m.heightPixels;
        }
        UI_DENSITY = m.density;
        UI_DENSITYDPI = m.densityDpi;
    }
}
