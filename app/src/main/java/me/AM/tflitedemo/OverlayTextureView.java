package me.AM.tflitedemo;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sqripter on 23/01/18.
 */

public class OverlayTextureView extends SurfaceView {

    private final Paint paint;
    private final SurfaceHolder mHolder;
    private final Context context;
    public Canvas canvas;
    public ArrayList<Rect> faces;
    public ArrayList<String> labels;


    public OverlayTextureView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public OverlayTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverlayTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.faces = new ArrayList<>();
        this.labels = new ArrayList<>();
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


        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(80);
        this.clear();
        for(int i=0; i<faces.size(); i++){
            if(faces.get(i) != null)
                canvas.drawRect(faces.get(i), paint);
                canvas.drawText(labels.get(i), faces.get(i).left, faces.get(i).bottom, paint);
        }

        mHolder.unlockCanvasAndPost(canvas);
    }

    public void clear(){
        Paint clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), clearPaint);
    }
}
