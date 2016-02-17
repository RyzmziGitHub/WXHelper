package wx.ry.org.wxhelper.friends;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import wx.ry.org.wxhelper.App;
import wx.ry.org.wxhelper.R;

/**
 * Created by renyang on 16/2/16.
 */
public class StarLayout extends FrameLayout{

    private TextView tvStart;
    private LinearLayout llFriends;

    public StarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public StarLayout(Context context) {
        super(context);
        init(context,null);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.view_friends, this);
        onFinishInflate();
    }

    public void setStar(String star){
        tvStart.setText(star);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvStart = (TextView)findViewById(R.id.tv_star_content);
        llFriends = (LinearLayout)findViewById(R.id.ll_friends);
        llFriends.setLayoutParams(new FrameLayout.LayoutParams(App.UI_SCREEN_WIDTH,App.UI_SCREEN_HEIGHT));
    }
}
