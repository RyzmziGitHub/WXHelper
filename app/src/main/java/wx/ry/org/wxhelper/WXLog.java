package wx.ry.org.wxhelper;

/**
 * Created by renyang on 16/2/18.
 */
public class WXLog {

    public static void i(String msg){
        base("I:",msg,2);
    }

    private static void base(String tag, String string, int level) {
        if (!BuildConfig.DEBUG)
            return;
        final StackTraceElement[] stack = new Throwable().getStackTrace();
        final StackTraceElement ste = stack[level];
        String classNameString = ste.getClassName();
        classNameString = classNameString.substring(classNameString.lastIndexOf(".") + 1, classNameString.length());

        android.util.Log.i("WXLog", "[" + classNameString + "." + ste.getMethodName() + " line:" + ste.getLineNumber() + "] >>    "
                + string);
    }
}
