package edu.illinois.cs.cs125.spring2019.lab12;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * alert.
 */
public class AlarmAlert extends Activity {
    /**
     * player.
     */
    private MediaPlayer mediaPlayer;

    /**
     * create.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.clockmusic2);
        mediaPlayer.start();
        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.clock)
                .setTitle("Warn!")
                .setMessage("It's time!")
                .setPositiveButton("turn off", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        AlarmAlert.this.finish();
                        mediaPlayer.stop();
                    }
                }).show();
    }
}

