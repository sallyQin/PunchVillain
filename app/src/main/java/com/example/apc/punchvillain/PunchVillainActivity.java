package com.example.apc.punchvillain;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class PunchVillainActivity extends AppCompatActivity  {  //（点击开打小人按钮后，跳转到的）“打小人”主界面
    SimpleDraweeView punchVillain_ImageShow;
    PunchVillainView punchVillainView;
    TextView text_villainName;
    RecyclerView recyclerView;
    SimpleDraweeView  UpImageFrame;
    ProgressView progressView;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_villain);
        punchVillain_ImageShow = (SimpleDraweeView) findViewById(R.id.punchVillain_ImageShow);
        assert punchVillain_ImageShow != null;
        punchVillain_ImageShow.setImageURI(SelectVillainActivity.UserChocenVillain_UriSaved);//衰人图像底层
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        PropSelectAdapter  propSelectAdapter = new PropSelectAdapter();
        PropSelectAdapter.punchVillainActivity = this;
        recyclerView.setAdapter(propSelectAdapter);
        text_villainName = (TextView) findViewById(R.id.text_villainName);//显示小人的姓名
        String getVillainName = getIntent().getStringExtra("衰人姓名");
        text_villainName.setText(getVillainName );
        punchVillainView = (PunchVillainView) findViewById(R.id.punchVillain_MidLayoutImage);//衰人图像中层
        UpImageFrame = (SimpleDraweeView) findViewById(R.id.punchVillain_UpImageFrame);//衰人图像最上层（火框）
        progressView = (ProgressView) findViewById(R.id.progress_view);//用于设置进度条
        assert progressView != null;
        progressView.setMaxCount(100.00f);//用于设置进度条最大值


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PunchVillain Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**用于设置进度条进度**/
    void updateProgressView (int counts){
        progressView.setCurrentCount(punchVillainView.countScore);

    }
}
