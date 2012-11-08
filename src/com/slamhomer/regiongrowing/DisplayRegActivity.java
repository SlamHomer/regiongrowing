package com.slamhomer.regiongrowing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DisplayRegActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_reg, menu);
        return true;
    }
    
    /** Called when the user clicks the Registrieren button */
    public void goReg(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
