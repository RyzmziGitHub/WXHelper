package wx.ry.org.wxhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wx.ry.org.wxhelper.friends.FriendsFragment;

/**
 * Created by renyang on 16/2/16.
 */
public abstract class BaseFragment extends Fragment{

    protected static final int TYPE_FRIENDS = 001;

    protected abstract int fromLayout();

    protected abstract void init(View view);

    public static BaseFragment newInstance(int type){
        if(type == TYPE_FRIENDS){
            return new FriendsFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(fromLayout(),null);
        init(view);
        return view;
    }
}
