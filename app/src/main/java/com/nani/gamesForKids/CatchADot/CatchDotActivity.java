package com.nani.gamesForKids.CatchADot;

import android.os.Bundle;
import android.view.View;

import com.nani.gamesForKids.R;
import org.apache.cordova.CordovaActivity;

public class CatchDotActivity extends CordovaActivity {

    private View mDecorView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_dot);

        this.mDecorView = getWindow().getDecorView();

        super.init();

        launchUrl = "file:///android_asset/catch_a_dot/www/index.html";
        loadUrl(launchUrl);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
