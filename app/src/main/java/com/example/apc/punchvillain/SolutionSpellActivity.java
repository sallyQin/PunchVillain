package com.example.apc.punchvillain;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Map;


public class SolutionSpellActivity extends AppCompatActivity {    //（主界面中按第2个按钮后，跳转到的）“求解符”主界面
    ImageView start_drawLots_btn;
    ImageView  drawLots_image;
    ImageView  goTo_treasure_btn;
    ImageView checkLots_button;
    public String mSavedNumStr;
    int mSaveRandomNum;
    private SoundPool mSoundPool;
    private Map<Integer, Integer> soundID = new HashMap<>();//记录声效

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution_spell);
        drawLots_image = (ImageView) findViewById(R.id.drawLots_image);
        goTo_treasure_btn = (ImageView) findViewById(R.id.GoTo_treasure_btn);
        checkLots_button = (ImageView) findViewById(R.id.checkLots_button);
        start_drawLots_btn = (ImageView) findViewById(R.id.start_drawLots_btn);
        initSoundRawRes();

        /**
         * 设置 第1个“开始抽符”按钮 监听器
         **/
        start_drawLots_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/**开启动画**/

                mSoundPool.play(soundID.get(2), 0.9f, 0.9f, 0, 1, 1);       // 添加音效
                final TranslateAnimation animation = new TranslateAnimation(-40, 80,-50, 100); //设置动画上下运动的幅度
                animation.setDuration(250);//设置动画持续时间
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
                animation.setRepeatCount(8);//设置重复次数
                drawLots_image.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {  //设置动画监听器
                    @Override
                    public void onAnimationStart(Animation animation) {
                        start_drawLots_btn.setVisibility(View.GONE);
                        goTo_treasure_btn.setVisibility(View.GONE);
                        mSaveRandomNum = (int)(Math.random()*4);
                        mSavedNumStr = "spell" + mSaveRandomNum;  //设置随机抽符签号码
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {  //当动画完成后，设置“占卜签”出桶
                        mSoundPool.play(soundID.get(1), 0.9f, 0.9f, 0, 0, 1);       // 添加音效
                        start_drawLots_btn.setVisibility(View.VISIBLE);
                        start_drawLots_btn.setBackgroundResource(R.drawable.lots_pic);
                        checkLots_button.setVisibility(View.VISIBLE);
                        checkLots_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SolutionSpellActivity.this,CheckDrawLotsActivity.class);
                                intent.setAction(Integer.toString(mSaveRandomNum));
                                intent.putExtra("随机抽符号",mSavedNumStr);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { //ignore

                    }
                });

            }
        });


        /**
         * 设置 第2个“新年财神签”按钮 监听器
         **/
        goTo_treasure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SolutionSpellActivity.this,LotteryHomeActivity.class);
                startActivity(intent);
            }
        });
    }


    /**初始化音效资源**/
    private void initSoundRawRes() {
        //设置最多可容纳2个音频流，音频的品质为5
        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        soundID.put(1, mSoundPool.load(this, R.raw.drawing_lottery, 1));
        soundID.put(2 , mSoundPool.load(this, R.raw.got_draw, 1));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!hasFocus){
            mSoundPool.release();
        }
    }
}
