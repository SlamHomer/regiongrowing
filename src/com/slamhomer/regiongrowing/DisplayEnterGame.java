package com.slamhomer.regiongrowing;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DisplayEnterGame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_enter_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_enter_game, menu);
        return true;
    }
}
