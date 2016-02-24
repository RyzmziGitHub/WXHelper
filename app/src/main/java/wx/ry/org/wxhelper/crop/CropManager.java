package wx.ry.org.wxhelper.crop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * Created by renyang on 16/2/24.
 */
public class CropManager {

    public static final int CODE_REQUEST = 0x001;
    public static final int CODE_CROP = 0x002;

    private Context context;
    private BitmapListener bitmapListener;

    public CropManager(Context context){
        this.context = context;
    }

    public void openPhoto(BitmapListener bitmapListener){
        this.bitmapListener = bitmapListener;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity)context).startActivityForResult(intent,CODE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_REQUEST) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
                bitmapListener.fail();
            }
        }
    }

    public interface BitmapListener{
        void cropBitmap(Bitmap bitmap);
        void fail();
    }
}
