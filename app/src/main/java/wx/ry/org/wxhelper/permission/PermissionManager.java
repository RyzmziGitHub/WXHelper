package wx.ry.org.wxhelper.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renyang on 16/2/26.
 */
public class PermissionManager {

    private static final int REQUEST_CODE_PERMISSION = 0X001;

    Context context;

    public PermissionManager(Context context){
        this.context = context;
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission))
                return false;
        }
        return true;
    }

    public void requestPermission() {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write External Storage");
        if(permissionsList.size() > 0){
            if(permissionsNeeded.size() > 0){
                String msg = "You need to grant access to";
                for(int i = 0; i< permissionsNeeded.size() ; i++){
                    String note = msg + ", "+permissionsNeeded.get(i);
                    showMessageOKCancel(note, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,permissionsList.toArray(new String[permissionsList.size()]),
                                    REQUEST_CODE_PERMISSION);
                        }
                    });
                }
                return ;
            }
            ActivityCompat.requestPermissions((Activity) context,permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_PERMISSION);
            return ;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
