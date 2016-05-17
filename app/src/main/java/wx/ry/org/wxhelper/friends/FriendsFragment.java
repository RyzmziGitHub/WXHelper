package wx.ry.org.wxhelper.friends;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import wx.ry.org.wxhelper.WXLog;
import wx.ry.org.wxhelper.base.BaseFragment;
import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.Util;
import wx.ry.org.wxhelper.main.MainActivity;

/**
 * Created by renyang on 16/2/16.
 */
public class FriendsFragment extends BaseFragment {
    @Override
    protected int fromLayout() {
        return R.layout.fragment_layout;
    }
    private Dictionary dictionary;
    private StarLayout starLayout;
    private ImageView ivCrop;
    private EditText etNum,etContent;
    @Override
    protected void init(final View view) {
        Button button = (Button)view.findViewById(R.id.button);
        ivCrop = (ImageView)view.findViewById(R.id.iv_header_icon);
        etNum = (EditText)view.findViewById(R.id.et_star_num);
        etContent = (EditText)view.findViewById(R.id.et_content);
        dictionary = new Dictionary(getActivity());
        starLayout = new StarLayout(getActivity());

        RxView.clicks(button).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                dictionary.getNickName(Integer.parseInt(etNum.getText().toString())).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        int height = starLayout.setStar(s, etContent.getText().toString(), null);
                        Bitmap bitmap = Util.loadBitmapFromView(starLayout,height);
                        ivCrop.setImageBitmap(bitmap);
                        //Util.saveIconForSDPath(bitmap);
                    }
                });
            }
        });
    }
}
