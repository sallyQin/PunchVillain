package com.example.apc.punchvillain;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.Map;

public class CheckDrawLotsActivity extends AppCompatActivity {    //“查看签符”主界面
    SimpleDraweeView draw_show;
    TextView draw_TxtName;
    int choseDrawName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_draw_lots);

        try {
            choseDrawName = Integer.parseInt(getIntent().getAction());
        } catch (NumberFormatException e) {
            // ignore
        }

        Map<Integer, String> map= new HashMap<>();
        map.put(0,"破小人符");
        map.put(1,"镇宅符");
        map.put(2,"发财符");
        map.put(3,"平安符");
        String mChosenDrawName = map.get(choseDrawName);
        draw_show = (SimpleDraweeView) findViewById(R.id.draw_show);
        draw_TxtName = (TextView) findViewById(R.id.draw_TxtName);
        String drawNameStr = getIntent().getStringExtra("随机抽符号");
        draw_TxtName.setText(mChosenDrawName);    //设置传递过来的符名
        draw_show.setImageURI(Uri.parse("res:///"+ getResources().getIdentifier(drawNameStr,"drawable",getPackageName())));//设置传递过来的符图
   }

}
