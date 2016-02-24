package wx.ry.org.wxhelper.crop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import wx.ry.org.wxhelper.R;

public class CropLayout extends FrameLayout {

    private Paint paint;
    private int spacing;

    public CropLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }

    public CropLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public CropLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        spacing = getResources().getDimensionPixelOffset(R.dimen.crop_spacing);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.dispatchDraw(canvas);
        int diff = (getHeight() - getWidth()) / 2;
        canvas.drawRect(new Rect(spacing, diff, getWidth() - spacing, diff + getWidth()), paint);
    }
}
