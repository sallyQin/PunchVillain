package com.example.apc.punchvillain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SelectVillainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mirror_frame;
    ImageView image_villain;
    LinearLayout mirror_layout;
    public static final int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_villain);
        image_villain = (ImageView) findViewById(R.id.image_villain);
        mirror_layout = (LinearLayout) findViewById(R.id.mirror_layout);
        mirror_frame = (ImageView) findViewById(R.id.mirror_frame);
        mirror_frame.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mirror_frame:

                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用android自带的照相机
//              Uri photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//              intent.putExtra("相机",photoUri.toString());
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            mirror_frame.setVisibility(View.INVISIBLE);
            mirror_layout.setVisibility(View.VISIBLE);
            Bundle bundle = data.getExtras();
            Bitmap bitmapSave = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            image_villain.setImageBitmap(bitmapSave);// 将图片显示在ImageView里
//            String data_image =getIntent().getStringExtra("相机");
//            Uri.parse(data_image);
        }
    }
}
