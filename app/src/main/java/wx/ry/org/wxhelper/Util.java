package wx.ry.org.wxhelper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.List;

import rx.functions.Func2;
import wx.ry.org.wxhelper.app.App;

/**
 * Created by renyang on 16/2/16.
 */
public class Util {

    public static Bitmap loadBitmapFromView(View v) {
        v.measure(App.UI_SCREEN_WIDTH,App.UI_SCREEN_HEIGHT);
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.draw(c);
        return b;
    }

    public static rx.Observable<String> listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        return rx.Observable.from(stringList).scan(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s+","+s2;
            }
        }).takeLast(1);

    }
}
