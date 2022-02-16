package com.example.helloandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

public class MainActivity16 extends AppCompatActivity implements UniversalVideoView.VideoViewCallback {

    // uvv_fitXY：视频拉伸适配video_view的尺寸
    // uvv_autoRotation：根据方向传感器自动切换横竖屏
    // uvv_scalable：显示或隐藏缩放按钮

    private FrameLayout video_layout;
    private LinearLayout button_layout;
    private UniversalVideoView video_view;
    private UniversalMediaController media_controller;
    private Button start;
    private TextView introduction;

    private static final String TAG = "MainActivity16";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "https://media.w3.org/2010/05/sintel/trailer.mp4";

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        setVideoAreaSize();
        video_view.setVideoViewCallback(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSeekPosition > 0) {
                    video_view.seekTo(mSeekPosition);
                }
                video_view.start();
                media_controller.setTitle("Game trailer video playing……");
            }
        });

        video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d(TAG, "Video On Completion……");
            }
        });

    }

    /**
     * 设置视频区域大小
     */
    private void setVideoAreaSize() {
        video_layout.post(new Runnable() {
            @Override
            public void run() {
                int width = video_layout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = video_layout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                video_layout.setLayoutParams(videoLayoutParams);
                video_view.setVideoPath(VIDEO_URL);
                video_view.requestFocus();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Video On Pause……");
        if (video_view != null && video_view.isPlaying()) {
            mSeekPosition = video_view.getCurrentPosition();
            Log.d(TAG, "Video On Pause mSeekPosition……" + mSeekPosition);
            video_view.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Video On Save Instance State Position=" + video_view.getCurrentPosition());
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSeekPosition = savedInstanceState.getInt(SEEK_POSITION_KEY);
        Log.d(TAG, "Video On Restore Instance State Position=" + mSeekPosition);
    }

    private void initView() {
        video_layout = findViewById(R.id.video_layout);
        video_view = findViewById(R.id.video_view);
        media_controller = findViewById(R.id.media_controller);
        start = findViewById(R.id.start);
        introduction = findViewById(R.id.introduction);
        button_layout = findViewById(R.id.button_layout);

    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullScreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = video_layout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            video_layout.setLayoutParams(layoutParams);
            button_layout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = video_layout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            video_layout.setLayoutParams(layoutParams);
            button_layout.setVisibility(View.VISIBLE);
        }
        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean b) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (b) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }


    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "Video On Pause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "Video On Start UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "Video On Buffering Start UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "Video On Buffering End UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        if (this.isFullScreen) {
            video_view.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }
}