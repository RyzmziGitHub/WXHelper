package wx.ry.org.wxhelper.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import wx.ry.org.wxhelper.R;

/**
 * Created by renyang on 16/2/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final int DEFAULT_REQUEST_CODE = 0x000;

    protected abstract  int fromLayout();

    protected abstract void init();

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fromLayout());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public static void startUIActivity(Context context,Class<?> cls,boolean isFinsh,int requestCode){
        BaseActivity instance = ((BaseActivity) context);
        Intent intent = new Intent(context,cls);
        instance.startActivityForResult(intent,requestCode);
        if(isFinsh){
            instance.finish();
        }
    }

    public static void startUIActivity(Context context,Class<?> cls){
        startUIActivity(context,cls,false,DEFAULT_REQUEST_CODE);
    }

}
