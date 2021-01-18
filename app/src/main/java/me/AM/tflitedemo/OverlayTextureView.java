package me.AM.tflitedemo;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

/**
 * Created by sqripter on 23/01/18.
 */

public class OverlayTextureView extends SurfaceView {

    private final Paint paint;
    private final SurfaceHolder mHolder;
    private final Context context;
    private Canvas canvas;
    public int xx;
    public int yy;

    public OverlayTextureView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public OverlayTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.xx = 0;
        this.yy = 0;
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setZOrderOnTop(true);
        mHolder = getHolder();
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // super.onDraw(canvas);
        setWillNotDraw(false);
        canvas = mHolder.lockCanvas();
        paint.setStyle(Paint.Style.FILL);
        this.canvas = canvas;

        int x = canvas.getWidth();
        int y = canvas.getHeight();


        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        Rect rect = new Rect(50, 50, x - xx, y - yy);
        canvas.drawRect(rect, paint);

        mHolder.unlockCanvasAndPost(canvas);
    }
}
