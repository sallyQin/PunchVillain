package com.example.apc.punchvillain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener { //整个App 的主界面
    Button button1;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.main_button1);
        button2 = (Button) findViewById(R.id.main_button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_button1:
                Intent intent1 = new Intent(MainActivity.this,SelectVillainActivity.class);
                startActivity(intent1);
                break;
            case R.id.main_button2:
                Intent intent2 = new Intent(MainActivity.this,SolutionSpellActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
