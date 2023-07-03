package com.auebds.coffeui.ui.tutorial;

        import android.content.Context;
        import android.content.Intent;
        import android.media.AudioManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.MediaController;
        import androidx.appcompat.app.AppCompatActivity;

        import com.auebds.coffeui.databinding.ActivityTutorialBinding;

public class TutorialActivity extends AppCompatActivity {

    private ActivityTutorialBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityTutorialBinding binding = ActivityTutorialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        Log.d("TUTORIAL", path);
        binding.tutorialVideo.setVideoPath(path);
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
        binding.tutorialVideo.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.tutorialVideo);
        binding.tutorialVideo.setMediaController(mediaController);
    }

}
