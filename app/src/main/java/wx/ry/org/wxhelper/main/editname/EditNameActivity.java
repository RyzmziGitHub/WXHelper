package wx.ry.org.wxhelper.main.editname;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.Util;
import wx.ry.org.wxhelper.app.App;
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
        String name = etEditName.getText().toString();
        if(!TextUtils.isEmpty(name)){
            Util.saveData(EditNameActivity.this, App.KEY_NAME,etEditName.getText().toString());
            setResult(MainActivity.CODE_EDIT_NAME, null);
        }
        super.onBackPressed();
    }
}
