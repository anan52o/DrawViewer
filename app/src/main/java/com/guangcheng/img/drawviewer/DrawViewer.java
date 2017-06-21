package com.guangcheng.img.drawviewer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class DrawViewer extends View {

    private static final String TAG = "DrawViewer";
    private int center = 0;

    public DrawViewer(Context context) {
        super(context);
        this.center = 30;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.i(TAG, "onDraw curr position = "+center);

        center += 10;
        if(center >= 1280) center = 0;

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(0x80);
        canvas.drawCircle(center,320,40,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0,360,1280,360,mPaint);

        super.onDraw(canvas);
    }
}
