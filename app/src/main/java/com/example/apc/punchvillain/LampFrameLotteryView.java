package com.example.apc.punchvillain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 1 on 2017/1/26.
 */

public class LampFrameLotteryView extends View { //“显示走马灯”的 自定义View
    private Paint mPaint;
    float left1_line1,left2_line1,left3_line1,left4_line1,left5_line1,left6_line1,left7_line1;
    float top_line1_edge, top_line1_per;
    float left1_row1,top1_row1,top2_row1,top3_row1,top4_row1,top5_row1;
    float left1_line2,left2_line2,left3_line2,left4_line2,left5_line2,left6_line2,left7_line2;
    float top_line2_edge,top_line2_per;
    float left1_row2,top1_row2,top2_row2,top3_row2,top4_row2,top5_row2;
    Bitmap mYellowBall_bitmap;//走马灯 黄灯bitmap图
    Bitmap mRedBall_bitmap;//走马灯 红灯bitmap图

    private boolean lampMode_1;

    public LampFrameLotteryView(Context context) {
        super(context);
        initView(context);
    }

    public LampFrameLotteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LampFrameLotteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  // 反锯齿
        mPaint.setTypeface(Typeface.SERIF);         // 衬线字体

        Thread thread = new Thread(new Runnable() { //改变闪灯模式（800毫秒改模式）
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(800);

                        post(new Runnable() {
                            @Override
                            public void run() {
                                if (lampMode_1) {
                                    lampMode_1 = false;
                                } else {
                                    lampMode_1 = true;
                                }
                                invalidate();
                            }});
                    }
                } catch (InterruptedException e) {

                }
            }
        });
        thread.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec); //获取自定义View的宽

        /** 布局走马灯“黄灯”位置 **/
        if(mYellowBall_bitmap == null){
            mYellowBall_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ball_yellow);//decode走马灯"黄灯"图
        int yellowBallBitmap_width = mYellowBall_bitmap.getWidth();
        int yellowBallBitmap_height = mYellowBall_bitmap.getHeight();
        int edgeSize = 20 + 20;
        int perPartWidth = (width-edgeSize-yellowBallBitmap_width)/6;
        top_line1_edge = 20;
        top_line1_per = 8;
        left1_line1 = 20;
        left2_line1 = 20 + perPartWidth;
        left3_line1 = 20 + perPartWidth*2;
        left4_line1 = 20 + perPartWidth*3;
        left5_line1 = 20 + perPartWidth*4;
        left6_line1 = 20 + perPartWidth*5;
        left7_line1 = width - yellowBallBitmap_width - 20;//以上为第一行每个闪灯的位置

        int perPartHeight = (width-edgeSize-yellowBallBitmap_height)/6;
        left1_row1 = 8;
        top1_row1 =  20 + perPartHeight;
        top2_row1 =  20 + perPartHeight* 2;
        top3_row1 =  20 + perPartHeight* 3;
        top4_row1 =  20 + perPartHeight* 4;
        top5_row1 = width - 20 - perPartHeight - yellowBallBitmap_height;//以上为第一列每个闪灯的位置

        top_line2_edge = width -20-yellowBallBitmap_height; // 2边点的y位置
        top_line2_per = width -8-yellowBallBitmap_height;  // 其余中间点的y位置
        left1_line2 = 20;
        left2_line2 = 20 + perPartWidth;
        left3_line2 = 20 + perPartWidth*2;
        left4_line2 = 20 + perPartWidth*3;
        left5_line2 = 20 + perPartWidth*4;
        left6_line2 = 20 + perPartWidth*5;
        left7_line2 = width - yellowBallBitmap_width - 20;//以上为第二行每个闪灯的位置

        left1_row2 =  width - yellowBallBitmap_width - 8;
        top1_row2 =  20 + perPartHeight;
        top2_row2 =  20 + perPartHeight* 2;
        top3_row2 =  20 + perPartHeight* 3;
        top4_row2 =  20 + perPartHeight* 4;
        top5_row2 = width - 20 - perPartHeight - yellowBallBitmap_height;//以上为第2列每个闪灯的位置
        }
        /** 布局走马灯“红灯”位置 **/
        if(mRedBall_bitmap == null){
            mRedBall_bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ball_purple);//decode走马灯"红灯"图
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lampMode_1) {
            /** 画走马灯“黄灯”位置 **/
            canvas.drawBitmap(mYellowBall_bitmap,left1_line1, top_line1_edge,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left2_line1, top_line1_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left3_line1, top_line1_per,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left4_line1, top_line1_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left5_line1, top_line1_per,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left6_line1, top_line1_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left7_line1, top_line1_edge,mPaint);//画 以上为第一行每个闪灯的位置
            canvas.drawBitmap(mRedBall_bitmap,left1_row1, top1_row1,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left1_row1, top2_row1,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left1_row1, top3_row1,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left1_row1, top4_row1,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left1_row1, top5_row1,mPaint);//画 以上为第一列每个闪灯的位置
            canvas.drawBitmap(mYellowBall_bitmap,left1_line2,top_line2_edge,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left2_line2,  top_line2_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left3_line2,  top_line2_per,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left4_line2, top_line2_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left5_line2, top_line2_per,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left6_line2,  top_line2_per,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left7_line2, top_line2_edge,mPaint);//画 以上为第2行每个闪灯的位置
            canvas.drawBitmap(mRedBall_bitmap,left1_row2, top1_row2,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left1_row2, top2_row2,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left1_row2, top3_row2,mPaint);
            canvas.drawBitmap(mYellowBall_bitmap,left1_row2, top4_row2,mPaint);
            canvas.drawBitmap(mRedBall_bitmap,left1_row2, top5_row2,mPaint);//画 以上为第2列每个闪灯的位置
        } else {
            /** 画走马灯“红灯”位置 **/
            canvas.drawBitmap(mRedBall_bitmap, left1_line1, top_line1_edge, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left2_line1, top_line1_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left3_line1, top_line1_per, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left4_line1, top_line1_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left5_line1, top_line1_per, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left6_line1, top_line1_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left7_line1, top_line1_edge, mPaint);//画 以上为第一行每个闪灯的位置
            canvas.drawBitmap(mYellowBall_bitmap, left1_row1, top1_row1, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left1_row1, top2_row1, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left1_row1, top3_row1, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left1_row1, top4_row1, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left1_row1, top5_row1, mPaint);//画 以上为第一列每个闪灯的位置
            canvas.drawBitmap(mRedBall_bitmap, left1_line2, top_line2_edge, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left2_line2, top_line2_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left3_line2, top_line2_per, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left4_line2, top_line2_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left5_line2, top_line2_per, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left6_line2, top_line2_per, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left7_line2, top_line2_edge, mPaint);//画 以上为第2行每个闪灯的位置
            canvas.drawBitmap(mYellowBall_bitmap, left1_row2, top1_row2, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left1_row2, top2_row2, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left1_row2, top3_row2, mPaint);
            canvas.drawBitmap(mRedBall_bitmap, left1_row2, top4_row2, mPaint);
            canvas.drawBitmap(mYellowBall_bitmap, left1_row2, top5_row2, mPaint);//画 以上为第2列每个闪灯的位置
        }
    }
}
