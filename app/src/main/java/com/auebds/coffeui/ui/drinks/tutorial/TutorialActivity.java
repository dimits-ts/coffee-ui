package com.auebds.coffeui.ui.drinks.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.databinding.ActivityTutorialBinding;

public class TutorialActivity extends AppCompatActivity {

    private ActivityTutorialBinding binding;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTutorialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        Log.d("what", path);
        binding.tutorialVideo.setVideoPath(path);
        binding.tutorialVideo.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.tutorialVideo);
        binding.tutorialVideo.setMediaController(mediaController);
    }


}
