package com.example.ui.todos.ultil;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class AudioPlayer {

    private static MediaPlayer mMediaPlayer;

    public static void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public static void play(Context c, int rid) {
        stop();

        mMediaPlayer = MediaPlayer.create(c, rid);
        mMediaPlayer.setOnCompletionListener(mediaPlayer -> stop());

        mMediaPlayer.start();
    }

    public static void play(Context c, String url) {
        stop();

        Uri uri = Uri.parse(url);
        mMediaPlayer = MediaPlayer.create(c, uri);
        mMediaPlayer.setOnCompletionListener(mediaPlayer -> stop());

        mMediaPlayer.start();
    }

}