package wx.ry.org.wxhelper.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import wx.ry.org.wxhelper.R;
import wx.ry.org.wxhelper.Util;
import wx.ry.org.wxhelper.app.App;
import wx.ry.org.wxhelper.base.BaseActivity;
import wx.ry.org.wxhelper.base.BaseFragment;
import wx.ry.org.wxhelper.main.crop.CropActivity;
import wx.ry.org.wxhelper.main.crop.CropManager;
import wx.ry.org.wxhelper.main.editname.EditNameActivity;
import wx.ry.org.wxhelper.permission.PermissionManager;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CODE_CROP = 0x0070;
    public static final int CODE_EDIT_NAME = 0x0071;

    private ImageView ivHeader;
    private TextView tvHeaderName;

    private int type = -1;

    @Override
    protected int fromLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ivHeader = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_header_icon);
        tvHeaderName = (TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_header_name);

        RxView.clicks(ivHeader).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startUIActivity(MainActivity.this, CropActivity.class);
            }
        });

        RxView.clicks(tvHeaderName).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startUIActivity(MainActivity.this, EditNameActivity.class);
            }
        });

        new PermissionManager(this).requestPermission();

        loadHeaderIcon();
        loadHeaderName();

        addFragment(BaseFragment.TYPE_FRIENDS);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int type = 0;
        if (id == R.id.star) {
            type = BaseFragment.TYPE_FRIENDS;
        }
        addFragment(type);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(int type){
        if(this.type == type) return;
        this.type = type;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, BaseFragment.newInstance(type)).commit();
    }

    private void loadHeaderIcon(){
        Bitmap bitmap = Util.getIconForAppPath(MainActivity.this);
        if(bitmap != null){
            ivHeader.setImageBitmap(bitmap);
        }
    }

    private void loadHeaderName(){
        String name = Util.getData(MainActivity.this, App.KEY_NAME);
        if(!TextUtils.isEmpty(name)){
            tvHeaderName.setText(name);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CODE_CROP) {
           loadHeaderIcon();
        }else if(resultCode ==  CODE_EDIT_NAME){
            loadHeaderName();
        }
    }
}
