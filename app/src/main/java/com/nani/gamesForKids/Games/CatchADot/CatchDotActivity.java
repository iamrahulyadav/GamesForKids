package com.nani.gamesForKids.Games.CatchADot;

import android.os.Bundle;

import com.nani.gamesForKids.Core.FullScreenGameCordovaActivity;
import com.nani.gamesForKids.R;

public class CatchDotActivity extends FullScreenGameCordovaActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_dot);
        super.init();

        launchUrl = "file:///android_asset/catch_a_dot/www/index.html";
        loadUrl(launchUrl);
    }
}
