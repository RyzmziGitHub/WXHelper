package wx.ry.org.wxhelper.friends;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import wx.ry.org.wxhelper.BaseFragment;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)view.findViewById(R.id.editText);
                Dictionary dictionary = new Dictionary(getActivity());
                List<String> list = dictionary.getNickName(Integer.parseInt(editText.getText().toString()));
                StarLayout starLayout = new StarLayout(getActivity());
                starLayout.setStar(Util.listToString(list));
                ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
                Bitmap bitmap = Util.loadBitmapFromView(starLayout);
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}
