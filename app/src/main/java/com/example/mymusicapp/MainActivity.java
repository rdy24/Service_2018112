package com.example.mymusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymusicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
  int i = 1;
  ActivityMainBinding binding;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    setTitle("2018112_Service Music Player");
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.btnPlay.setOnClickListener(view -> startService(new Intent(MainActivity.this, MusicService.class)));

    binding.btnNext.setOnClickListener(view -> {
      if (i > 6) {
        i = 0;
      }
      Intent intent = new Intent(MainActivity.this, MusicService.class);
      intent.putExtra("position", i++);
      startService(intent);
    });

    binding.btnPrev.setOnClickListener(view -> {
      if (i < 0) {
        i = 6;
      }
      Intent intent = new Intent(MainActivity.this, MusicService.class);
      intent.putExtra("position", i--);
      startService(intent);
    });

    binding.btnPause.setOnClickListener(view -> {
      if (MusicService.mediaPlayer.isPlaying()) {
        MusicService.mediaPlayer.pause();
        binding.btnPause.setText("Continue");
      } else {
        MusicService.mediaPlayer.start();
        binding.btnPause.setText("Pause");
      }
    });

    binding.btnStop.setOnClickListener(view -> {
      MusicService.mediaPlayer.stop();
    });
  }
}