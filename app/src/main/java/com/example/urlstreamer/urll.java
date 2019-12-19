package com.example.urlstreamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class urll extends AppCompatActivity {
    private PlayerView playerview;
    private SimpleExoPlayer player;
    String urlinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urll);
        playerview = findViewById(R.id.videoView);
        /*this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playerview.getLayoutParams();
        params.width=params.MATCH_PARENT;
        params.height=params.MATCH_PARENT;
        playerview.setLayoutParams(params);
        */


        Intent intent1 = getIntent();
        urlinput = intent1.getStringExtra("extra");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TrackSelector trackSelector = new DefaultTrackSelector();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"VideoPlayer"));

        MediaSource videosource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(urlinput));
        ExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this,trackSelector);

        playerview.setPlayer(exoPlayer);
        exoPlayer.prepare(videosource);
        exoPlayer.setPlayWhenReady(true);

        //landscape mode

    }

    @Override
    protected void onStop() {
        super.onStop();
        playerview.setPlayer(null);
        player.release();
        player=null;
    }
}
