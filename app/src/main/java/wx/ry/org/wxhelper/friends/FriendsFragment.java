package wx.ry.org.wxhelper.friends;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import wx.ry.org.wxhelper.base.BaseFragment;
import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.Util;

/**
 * Created by renyang on 16/2/16.
 */
public class FriendsFragment extends BaseFragment {
    @Override
    protected int fromLayout() {
        return R.layout.fragment_layout;
    }

    @Override
    protected void init(final View view) {
        Button button = (Button)view.findViewById(R.id.button);
        RxView.clicks(button).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                EditText editText = (EditText)view.findViewById(R.id.editText);
                Dictionary dictionary = new Dictionary(getActivity());
                final StarLayout starLayout = new StarLayout(getActivity());
                dictionary.getNickName(Integer.parseInt(editText.getText().toString())).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        starLayout.setStar(s);
                    }
                });
                ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
                Bitmap bitmap = Util.loadBitmapFromView(starLayout);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
