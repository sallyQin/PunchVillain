package com.example.apc.punchvillain;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.util.ArrayList;

public class SelectVillainActivity extends AppCompatActivity implements View.OnClickListener {   //（主界面中按第1个按钮后，跳转到的选小人）“打小人”主界面
    View wholeFrame;
    ImageView mirror_frame;
    FrameLayout show_villain_frame;
    SimpleDraweeView image_villain;
    EditText villainName_editTxt;
    TextView camera;
    TextView gallery;
    TextView selfDefine;
    TextView cancel;
    LinearLayout mirror_layout;
    LinearLayout content_choice;
    LinearLayout confirm_linerLayout;
    Button button_change_pic;
    Button button_punch_villain;
    String mFilePath;
    static Uri UserChocenVillain_UriSaved;
    Uri CapturedUri;//拍照获取的uri
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_villain);
        initDataHolder();//初始化ListViewAdapter
        mirror_layout = (LinearLayout) findViewById(R.id.mirror_layout);
        wholeFrame = findViewById(R.id.wholeFrame);
        wholeFrame.setOnClickListener(this);
        mirror_frame = (ImageView) findViewById(R.id.mirror_frame);
        content_choice = (LinearLayout) findViewById(R.id.content_choice);
        show_villain_frame = (FrameLayout) findViewById(R.id.show_villain);
        image_villain = (SimpleDraweeView) findViewById(R.id.image_villain);
        confirm_linerLayout = (LinearLayout) findViewById(R.id.confirm_linerLayout);
        cancel = (TextView) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        camera = (TextView) findViewById(R.id.camera); //相机
        camera.setOnClickListener(this);
        gallery = (TextView) findViewById(R.id.gallery);//相册
        gallery.setOnClickListener(this);
        selfDefine = (TextView) findViewById(R.id.selfDefine); //系统默认图
        selfDefine.setOnClickListener(this);
        button_change_pic = (Button) findViewById(R.id.button_change_pic);//重选图片 的按钮
        button_change_pic.setOnClickListener(this);
        button_punch_villain = (Button) findViewById(R.id.button_punch_villain);//开打小人 的按钮
        button_punch_villain.setOnClickListener(this);
        villainName_editTxt = (EditText) findViewById(R.id.villainName_editTxt);//小人名字文本框
    }


    private ArrayList<ListItemAdapter.DataHolder> initDataHolder(){ //dialog里的items数据
        ArrayList<ListItemAdapter.DataHolder> dataList = new ArrayList<>();
        ListItemAdapter.DataHolder data_1 = new ListItemAdapter.DataHolder("男衰人", R.drawable.male_default);
        ListItemAdapter.DataHolder data_2 = new ListItemAdapter.DataHolder("女衰人", R.drawable.female_default);
        dataList.add(data_1);
        dataList.add(data_2);
        return dataList;
    }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        ContentValues contentValues = new ContentValues(1);
//        contentValues.put(MediaStore.Images.Media.DATA, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ "CM_CAPTURED_" + System.currentTimeMillis() +  ".jpg");
//        Uri CapturedUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, CapturedUri);// 更改系统默认存储路径
//        startActivityForResult(intent, REQUEST_CAMERA);


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wholeFrame:
                toChoiceContentSetting();
                break;
            case R.id.camera:   /** 系统相机 */
                openCamera();
                break;
            case R.id.gallery: /** 系统相册 */
                openGallery();
                break;
            case R.id. selfDefine:/** 系统默认图片选择 */
                createCustomList();
                break;
            case R.id.cancel:/** 取消按钮 */
                initSetting();
                break;
            case R.id.button_change_pic:/** 重选图片 */
                toChoiceContentSetting();
                break;
            case R.id.button_punch_villain:/** 开打小人 */
                Intent intent = new Intent(SelectVillainActivity.this,PunchVillainActivity.class);
                Drawable drawable = image_villain.getDrawable();
                intent.putExtra("用户已选图像",drawable.toString());
                if(!TextUtils.isEmpty(villainName_editTxt.getText())){
                    intent.putExtra("衰人姓名",villainName_editTxt.getText().toString());
                }
                startActivity(intent);
                break;
        }
    }


    /** 添加订制 dialog**/
    private void createCustomList() {
        ArrayList<ListItemAdapter.DataHolder> dataHolders = initDataHolder();
        ListItemAdapter adapter = new ListItemAdapter(SelectVillainActivity.this, dataHolders);
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectVillainActivity.this);
        builder.setIcon(R.drawable.default_villain_icon);
        builder.setTitle("选小人");

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String default_villain_selected = "默认男衰人！";
                showVillainUI();
                if(which == 0){
                    image_villain.setImageURI(Uri.parse("res:///"+R.drawable.male_default));
                    UserChocenVillain_UriSaved = Uri.parse("res:///"+R.drawable.male_default);//保存用户所选图的uri.
                    default_villain_selected = "默认男衰人！";
                }else if(which == 1){
                    image_villain.setImageURI(Uri.parse("res:///"+R.drawable.female_default));
                    UserChocenVillain_UriSaved = Uri.parse("res:///"+R.drawable.female_default);//保存用户所选图的uri.
                    default_villain_selected = "默认女衰人！";
                }
                Toast.makeText(SelectVillainActivity.this, "已选：" +  default_villain_selected, Toast.LENGTH_LONG).show();
            }
        });
        Window dialogWindow = builder.show().getWindow();//设置老dialog位置
        assert dialogWindow != null;
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        lp.x = 0; // 新位置X坐标
        lp.y = 1; // 新位置Y坐标
        lp.width = content_choice.getWidth(); // dialog宽度
        lp.height = content_choice.getHeight()-1; // dialog高度
        lp.alpha = 0.78f; // 透明度
        dialogWindow.setAttributes(lp);
    }


    /** "魔镜"初始界面**/
    private void initSetting(){
        mirror_frame.setVisibility(View.VISIBLE);
        mirror_layout.setVisibility(View.INVISIBLE);
        show_villain_frame.setVisibility(View.INVISIBLE);
        confirm_linerLayout.setVisibility(View.INVISIBLE);

    }

    /** 点击"魔镜"框进入内容选择界面**/
    private void toChoiceContentSetting(){
        mirror_frame.setVisibility(View.INVISIBLE);
        mirror_layout.setVisibility(View.VISIBLE);
        show_villain_frame.setVisibility(View.INVISIBLE);
        content_choice.setVisibility(View.VISIBLE);
        confirm_linerLayout.setVisibility(View.INVISIBLE);
    }



    /** 用户选择图片后，返回的页面**/
    private void showVillainUI(){
        mirror_layout.setVisibility(View.VISIBLE);
        wholeFrame.setEnabled(false);
        mirror_frame.setVisibility(View.INVISIBLE);
        content_choice.setVisibility(View.GONE);
        show_villain_frame.setVisibility(View.VISIBLE);
        confirm_linerLayout.setVisibility(View.VISIBLE);
    }

    /** 拍照后存储并显示图片**/
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
            mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/CM_CAPTURED_" + System.currentTimeMillis() + ".jpg";//相机的存储路径(有点忘了，跟context.get......私有存储和公有存储的区别？)
            CapturedUri = Uri.fromFile(new File(mFilePath));// 传递路径
            intent.putExtra(MediaStore.EXTRA_OUTPUT, CapturedUri);// 更改系统默认存储路径
            startActivityForResult(intent, REQUEST_CAMERA);
    }

    /** 打开系统相册**/
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {
            showVillainUI();
            if (requestCode == REQUEST_CAMERA) {//获取相机返回的数据图并显示
                image_villain.setImageURI(CapturedUri);
                UserChocenVillain_UriSaved = CapturedUri;//保存用户所选图的uri.

            } else if (requestCode == REQUEST_GALLERY) {//显示用户所选的相册图片
                Uri uri = data.getData();
                image_villain.setImageURI(uri);
                UserChocenVillain_UriSaved = Uri.parse(uri.toString());//保存用户所选图的uri.
            }

        }
    }
}