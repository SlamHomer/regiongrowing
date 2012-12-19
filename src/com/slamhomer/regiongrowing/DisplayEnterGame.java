package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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
    
    public void goStart(View view) {
		Intent intent = new Intent(this, DisplayGame.class);
		startActivity(intent);
	}
}
