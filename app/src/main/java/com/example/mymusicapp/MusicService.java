package com.example.mymusicapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
  static MediaPlayer mediaPlayer;

  int[] music = {
      R.raw.wildwest,
      R.raw.battlesumeru,
      R.raw.ormosgenshin,
      R.raw.scaramouse,
      R.raw.sumerucity, R.raw.sanctuary,
          R.raw.wanderer,
  };

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    int position = intent.getIntExtra("position", 0);
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.release();
    }

    mediaPlayer = MediaPlayer.create(this, music[position]);
    mediaPlayer.setLooping(true);
    mediaPlayer.start();
    return START_REDELIVER_INTENT;
  }
}
