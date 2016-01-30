package cn.campusapp.rtmpplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private static final String KEY_PATH = "key_path";


    @Bind(R.id.vitamio_videoView)
    VideoView vVideoView;


    public static Intent makeIntent(String rtmpPath){
        Intent intent = new Intent(App.getContext(), PlayerActivity.class);
        intent.putExtra(KEY_PATH, rtmpPath);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);
        String path = getIntent().getStringExtra(KEY_PATH);
        initVideo(path);
    }


    private void initVideo(String path){
        HashMap<String, String> options = new HashMap<>();
        options.put("rtmp_live", "1");
        vVideoView.setVideoURI(Uri.parse(path), options);
        vVideoView.setMediaController(new MediaController(this));
        vVideoView.requestFocus();

        vVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }
}
