package wx.ry.org.wxhelper.main.crop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import uk.co.senab.photoview.PhotoViewAttacher;
import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.Util;
import wx.ry.org.wxhelper.base.BaseActivity;

/**
 * Created by renyang on 16/2/24.
 */
public class CropActivity extends BaseActivity {

    private CropManager cropManager;
    private ImageView ivIcon;
    private PhotoViewAttacher attacher;
    private TextView tvOkey;
    @Override
    protected int fromLayout() {
        return R.layout.activity_crop;
    }

    @Override
    protected void init() {
        ivIcon = (ImageView)findViewById(R.id.iv_icon);
        cropManager = new CropManager(this);
        attacher = new PhotoViewAttacher(ivIcon);
        tvOkey = (TextView)findViewById(R.id.tv_okey);
        cropManager.openPhoto(new CropManager.BitmapListener() {
            @Override
            public void cropBitmap(Bitmap bitmap) {
                ivIcon.setImageBitmap(bitmap);
                attacher.update();
            }

            @Override
            public void fail() {

            }
        });
        RxView.clicks(tvOkey).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Bitmap bitmap = cropManager.crop(ivIcon, attacher);
                Intent intent = new Intent();
                intent.setData(Util.getUribyBitmap(CropActivity.this,bitmap));
                setResult(CropManager.CODE_CROP, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cropManager.onActivityResult(requestCode, resultCode, data);
    }
}
