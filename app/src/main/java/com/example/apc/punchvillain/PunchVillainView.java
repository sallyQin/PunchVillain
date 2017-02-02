package com.example.apc.punchvillain;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.example.apc.punchvillain.Data.PunchActionData;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PunchVillainView extends SimpleDraweeView {  // “打小人界面”的中间部分-自定义View
    static PropSelectAdapter propSelectAdapter;
    PunchVillainActivity punchVillainActivity;
    private Paint mPaint;
    int countScore;//点击后道具分总和
    float x, y;//用户触摸点
    float saved_xPostion,saved_yPostion;//暂时记录道具的x,y触摸点
    int saved_propPicDrawable;//暂时记录道具
    List<PunchActionData> propList = new ArrayList<>();//添加道具&触摸点List
    private SoundPool mSoundPool;
    private Map<Integer, Integer> soundID = new HashMap<>();//记录声效

    public PunchVillainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PunchVillainView(Context context) {
        super(context);
        initView(context);
    }

    public PunchVillainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PunchVillainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);

    }

    private void initView(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  // 反锯齿
        mPaint.setTypeface(Typeface.SERIF);         // 衬线字体
        punchVillainActivity = (PunchVillainActivity) context;
        initSoundRawRes();
    }

    /**初始化音效资源**/
    private void initSoundRawRes() {
        //设置最多可容纳8个音频流，音频的品质为5
        mSoundPool = new SoundPool(8, AudioManager.STREAM_SYSTEM, 5);
        soundID.put(1, mSoundPool.load(getContext(), R.raw.axe, 1));
        soundID.put(2 , mSoundPool.load(getContext(), R.raw.dirt, 1));
        soundID.put(3, mSoundPool.load(getContext(), R.raw.fist, 1));
        soundID.put(4, mSoundPool.load(getContext(), R.raw.painting, 1));
        soundID.put(5, mSoundPool.load(getContext(), R.raw.plam, 1));
        soundID.put(6, mSoundPool.load(getContext(), R.raw.throwthing, 1));
        soundID.put(7, mSoundPool.load(getContext(), R.raw.shuai, 1));
        soundID.put(8, mSoundPool.load(getContext(), R.raw.fire_plam, 1));
        soundID.put(9, mSoundPool.load(getContext(), R.raw.shout, 1));
        soundID.put(10, mSoundPool.load(getContext(), R.raw.dead, 1));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                x = MotionEventCompat.getX(event, 0);
                y = MotionEventCompat.getY(event, 0);
                if(countScore < 100 && propSelectAdapter.propPicDrawable!= null ){
                    if(propSelectAdapter.propPicDrawable == R.drawable.color_prop){   //设置喷枪的9种彩喷随机色
                        int randomNum = (int)(Math.random()*9);
                        String resultStr = "a" + randomNum;
                        int resId = getResources().getIdentifier(resultStr,"drawable",getContext().getPackageName());
                        if (0 == resId) {
                            break;
                        }
                        saved_propPicDrawable = resId;
                        mSoundPool.play(soundID.get(4), 0.7f, 0.7f, 0, 0, 1);    //添加音效
                    }else{
                        saved_propPicDrawable = propSelectAdapter.propPicDrawable;
                        mSoundPool.play(soundID.get( propSelectAdapter.soundGetPosition_saved), 0.9f, 0.9f, 0, 0, 1);   //添加音效
                    }
                    saved_xPostion = x;
                    saved_yPostion = y;
                    propList.add(new PunchActionData( saved_propPicDrawable,saved_xPostion,saved_yPostion));
                    countScore = propSelectAdapter.propScore_saved + countScore;
                    punchVillainActivity.updateProgressView(countScore);}
                else if (countScore >= 100 && propSelectAdapter.propPicDrawable!= null) {    //当达到暴打最高值时。。。
                    mSoundPool.play(soundID.get(9), 0.8f, 0.5f, 0, 0, 1);//添加音效
                    mSoundPool.play(soundID.get(10), 0.9f, 0.5f, 0, -1, 0.8f);//添加音效
                    punchVillainActivity.updateProgressView(100);//最大值设为100.
                    setVisibility(INVISIBLE);
                    punchVillainActivity.UpImageFrame.setVisibility(VISIBLE);

                }
                break;
            case (MotionEvent.ACTION_MOVE):
                x = MotionEventCompat.getX(event, 0);
                y = MotionEventCompat.getY(event, 0);
                break;
            case (MotionEvent.ACTION_UP):
                invalidate();
                break;
            case (MotionEvent.ACTION_CANCEL):
                break;
        }
        return  true;

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus){
             mSoundPool.release();   //回收SoundPool资源
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if( propList.size()> 0){
            for(int i = 0;i< propList.size();i++){
                PunchActionData punchActionData = propList.get(i);
                Bitmap prop_bitmap = BitmapFactory.decodeResource(getResources(),punchActionData.mDrawableRes);//decode出道具图
                int mBitWidth = prop_bitmap.getWidth();
                int mBitHeight = prop_bitmap.getHeight();
                Rect mSrcRect = new Rect(0, 0, mBitWidth, mBitHeight);
                // 计算左边位置(取道具图中间位)
                int left = (int) punchActionData.x - mBitWidth/2;
                // 计算上边位置(取道具图中间位)
                int top = (int)  punchActionData.y - mBitHeight/2;
                Rect mDestRect = new Rect(left, top, left + mBitWidth, top + mBitHeight);
                if (! prop_bitmap.isRecycled()) {
                    canvas.drawBitmap( prop_bitmap, mSrcRect, mDestRect, mPaint);
                }
            }
        }

    }

}
