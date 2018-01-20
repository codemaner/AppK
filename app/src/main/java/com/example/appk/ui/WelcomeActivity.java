package com.example.appk.ui;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.appk.R;
import com.example.appk.ui.baseActivity.BaseActivity;
import com.example.appk.widget.CustomVideoView;

public class WelcomeActivity extends BaseActivity {
    private Button welcome_button;
    private CustomVideoView welcome_videoview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome_button=this.findViewById(R.id.welcome_button);
        welcome_videoview = this.findViewById(R.id.welcome_videoview);
        welcome_videoview.setVideoURI(Uri.parse("android.resource://"+this.getPackageName()+"/"+R.raw.kr36));
        welcome_videoview.start();

        //循环播放
        welcome_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                welcome_videoview.start();
            }
        });
        welcome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (welcome_videoview.isPlaying()){
                    welcome_videoview.stopPlayback();
                    welcome_videoview = null;
                }
                //点击按钮跳转到MainActivity
                openActivity(MainActivity.class);
                WelcomeActivity.this.finish();

            }
        });
    }

    private  String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
