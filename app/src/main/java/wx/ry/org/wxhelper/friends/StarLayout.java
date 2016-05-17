package wx.ry.org.wxhelper.friends;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import wx.ry.org.wxhelper.Util;
import wx.ry.org.wxhelper.WXLog;
import wx.ry.org.wxhelper.app.App;
import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.main.MainActivity;
import wx.ry.org.wxhelper.widget.CenterImageSpan;

/**
 * Created by renyang on 16/2/16.
 */
public class StarLayout extends FrameLayout{

    private TextView tvStart;
    private LinearLayout llFriends;
    private Context context;
    private ImageView ivHeaderIcon,ivContent;
    private TextView tvName,tvContent;
    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

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
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        inflate(context, R.layout.view_friends, this);
        onFinishInflate();

    }

    public int setStar(String star,String content,Bitmap pic){
        spannableStringBuilder.clear();
        spannableStringBuilder.append("  " + star);
        spannableStringBuilder.setSpan(new CenterImageSpan(context, R.drawable.heart), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvStart.setText(spannableStringBuilder);

        Bitmap bitmap = Util.getIconForAppPath(context);
        if(bitmap != null){
            ivHeaderIcon.setImageBitmap(bitmap);
        }
        String name = Util.getData(context,  App.KEY_NAME);
        if(!TextUtils.isEmpty(name)){
            tvName.setText(name);
        }
        if(TextUtils.isEmpty(content)){
            Util.setVisibility(tvContent,GONE);
        }else{
            Util.setVisibility(tvContent, VISIBLE);
            tvContent.setText(content);
        }
        if(pic == null){
            Util.setVisibility(ivContent,GONE);
        }else{
            Util.setVisibility(ivContent,VISIBLE);
            ivContent.setImageBitmap(pic);
        }
        NinePatchDrawable ninePatchDrawable = (NinePatchDrawable)tvStart.getBackground();
        int height = Util.getHeight(context,star,tvStart.getTextSize(),ninePatchDrawable.getIntrinsicWidth());
        int defaulHeightt = context.getResources().getDimensionPixelOffset(R.dimen.view_friend_dfault_height);
        WXLog.i("height:"+height);
        return height + defaulHeightt;
    }

    public void getLayoutHeight(){

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvStart = (TextView)findViewById(R.id.tv_star_content);
        llFriends = (LinearLayout)findViewById(R.id.ll_friends);
        llFriends.setLayoutParams(new FrameLayout.LayoutParams(App.UI_SCREEN_WIDTH,App.UI_SCREEN_HEIGHT));
        ivHeaderIcon = (ImageView)findViewById(R.id.iv_header_icon);
        tvName = (TextView)findViewById(R.id.tv_name);
        tvContent = (TextView)findViewById(R.id.tv_content_text);
        ivContent = (ImageView)findViewById(R.id.iv_content_image);
    }
}
