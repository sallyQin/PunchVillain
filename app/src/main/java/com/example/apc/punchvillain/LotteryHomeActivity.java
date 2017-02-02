package com.example.apc.punchvillain;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryHomeActivity extends Activity {   //九宫格抽奖“主界面”
	
	LotteryView lottery_lots;
	LampFrameLotteryView frame_lottery_image;
    FrameLayout frame;
	private SoundPool mSoundPool;
	private Map<Integer, Integer> soundID = new HashMap<>();//记录声效
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lottery_home);
        frame= (FrameLayout) findViewById(R.id.frame);
		lottery_lots =(LotteryView) findViewById(R.id.lottery_lots);
		frame_lottery_image = (LampFrameLotteryView) findViewById(R.id.frame_lottery);
		initSoundRawRes();//初始化音效

		int[]prizesIcon={R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4, R.drawable.t5, R.drawable.t6, R.drawable.t7, R.drawable.t8, R.drawable.t9};
		String[] lotteryNameStr = new String[]{"财源滚滚来！","鸡年中新股","鸡年大吉大利","新年大红包","","2017年业绩大涨","sorry！重新抽吧！","新年步步高升","2017数钱数到手抽筋！"};
		final List<Prize> prizes=new ArrayList<Prize>();
		for(int x=0;x<9;x++){
			Prize lottery=new Prize();
			lottery.setId(x+1);
			lottery.setName("你抽中的财神签为："+lotteryNameStr[x]);//设置抽签的中签名
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), prizesIcon[x]);
			lottery.setIcon(bitmap);
			if((x+1)%2==0){
				lottery.setBgColor(0xe0FFC1C1);
			}else if(x==4){
				lottery.setBgColor(0xffffffff);
			}else{
				lottery.setBgColor(0xe0EEE8AA);
			}
			
			prizes.add(lottery);
		}
		lottery_lots.setPrizes(prizes);
		lottery_lots.setOnTransferWinningListener(new LotteryView.OnTransferWinningListener() {
													  @Override
													  public void onWinning(int position) {
														  Toast.makeText(getApplicationContext(), prizes.get(position).getName(), Toast.LENGTH_SHORT).show();
													  }
												  });
	}

	/**初始化音效资源**/
	private void initSoundRawRes() {
		//设置最多可容纳1个音频流，音频的品质为5
		mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
		soundID.put(1, mSoundPool.load(this, R.raw.speed_dial, 1));
		//soundID.put(2 , mSoundPool.load(this, R.raw.dirt, 1));
	}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            frame.getLayoutParams().height = frame.getWidth(); //预设view高度 等同于 "宽度"
            frame.requestLayout();//真正设置view高度
            mSoundPool.play(soundID.get(1), 0.9f, 0.9f, 0, -1, 0.5f);    //添加音效
        }else{
            mSoundPool.release();// 释放音效资源，并停止音效。
        }
    }
}
