package wx.ry.org.wxhelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import rx.functions.Func2;
import wx.ry.org.wxhelper.app.App;

/**
 * Created by renyang on 16/2/16.
 */
public class Util {

    public static Bitmap loadBitmapFromView(View v) {
        v.measure(App.UI_SCREEN_WIDTH, App.UI_SCREEN_HEIGHT);
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.draw(c);
        return b;
    }

    public static Bitmap getBitmapByUri(Context context, Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveIconForAppPath(Context context, Bitmap bitmap) {
        File file = new File(context.getFilesDir().getPath());
        if (!file.exists()) {
            WXLog.i("dir:" + file.mkdir());
        }
        File fileIcon = new File(context.getFilesDir().getPath() + "/crop.png");
        try {
            boolean b = fileIcon.createNewFile();
            if (b) {
                FileOutputStream fos = new FileOutputStream(fileIcon);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            WXLog.i("e:" + e.toString());
            e.printStackTrace();
        }
    }

    public static Bitmap getIconForAppPath(Context context) {
        String path = context.getFilesDir().getPath() + "/crop.png";
        File file = new File(path);
        if (file.exists()) {
            return BitmapFactory.decodeFile(path);
        }
        return null;
    }
}
