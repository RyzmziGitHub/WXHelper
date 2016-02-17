package wx.ry.org.wxhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by renyang on 16/2/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

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

    public static void startUIActivity(Context context,Class<?> cls,boolean isFinsh){
        BaseActivity instance = ((BaseActivity) context);
        Intent intent = new Intent(context,cls);
        instance.startActivity(intent);
        if(isFinsh){
            instance.finish();
        }
    }

    public static void startUIActivity(Context context,Class<?> cls){
        startUIActivity(context,cls,false);
    }

}
