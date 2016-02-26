package wx.ry.org.wxhelper.main.editname;

import android.content.Intent;
import android.widget.EditText;

import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.base.BaseActivity;
import wx.ry.org.wxhelper.main.MainActivity;

/**
 * Created by renyang on 16/2/26.
 */
public class EditNameActivity extends BaseActivity{

    private EditText etEditName;

    @Override
    protected int fromLayout() {
        return R.layout.activity_editname;
    }

    @Override
    protected void init() {
        etEditName = (EditText)findViewById(R.id.et_edit_name);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(MainActivity.CODE_EDIT_NAME);
    }
}
