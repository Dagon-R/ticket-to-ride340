package Communication;

import android.media.MediaPlayer;

public class SongPlayer extends Thread {
    public void setTurn_on(boolean turn_on) {
        this.turn_on = turn_on;
    }

    private boolean turn_on = true;
    private MediaPlayer mediaPlayer;
    public SongPlayer(MediaPlayer mediaPlayer, boolean turn_on)
    {
        this.turn_on = turn_on;
        this.mediaPlayer = mediaPlayer;
    }
    @Override
    public void run() {
        if (turn_on)
        {
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }
}
