package info.androidhive.listviewfeed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    ImageButton imb_play;
    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        myVideoView = (VideoView) findViewById(R.id.myVideo1);
        Intent i = getIntent();
        String path = i.getBundleExtra("bundle").getString("key");

        mediaControls = new MediaController(VideoActivity.this);
        mediaControls.setAnchorView(myVideoView);
        myVideoView.setMediaController(mediaControls);

        //Uri vidUri = Uri.parse(path);
        //myVideoView.setVideoURI(vidUri);
        myVideoView.setVideoPath(path);
        myVideoView.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            current = myVideoView.getCurrentPosition();
            Log.d("aaaaaaaaaaaaaaaa", current + "");
            Bundle bundle = new Bundle();
            bundle.putInt("currentTime", current);
            Intent i = new Intent(VideoActivity.this, ProfileActivity.class);
            i.putExtra("bundle", bundle);
            startActivity(i);
            return super.onKeyDown(keyCode, event);

        } else {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
