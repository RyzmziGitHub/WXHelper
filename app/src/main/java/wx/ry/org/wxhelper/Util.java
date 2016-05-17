package wx.ry.org.wxhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

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

    public static Bitmap loadBitmapFromView(View view,int height) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), height);
        view.draw(canvas);
        return bitmap;
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
        bitmapToFile(context.getFilesDir().getPath() + "/crop.png",bitmap);
    }

    public static boolean saveIconForSDPath(Bitmap bitmap){
        File file = new File(Environment.getExternalStorageDirectory(), "wx");
        if (!file.exists()) {
            WXLog.i("dir:" + file.mkdir());
        }
        bitmapToFile(file.getPath() + "/"+System.currentTimeMillis()+".png",bitmap);
        return true;
    }

    private static void bitmapToFile(String path,Bitmap bitmap){
        File fileIcon = new File(path);
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

    public static void saveData(Context context,String key,String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getData(Context context,String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key,"");
    }

    public static void setVisibility(View view,int visibility){
        if(view.getVisibility() == visibility){
            return ;
        }
        view.setVisibility(visibility);
    }

    public static int getHeight(Context context, String text, float textSize, int deviceWidth) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }
}
