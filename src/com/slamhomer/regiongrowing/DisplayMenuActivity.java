package com.slamhomer.regiongrowing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class DisplayMenuActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);
    }
	
	
	/** Called when the user clicks the Exit button */
	public void goLogout(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	/** Called when the user clicks the Weiter button */
    public void goContinue(View view) {
		Intent intent = new Intent(this, DisplayEnterGame.class);
		startActivity(intent);
	}
    
    /** Called when the user clicks the Neues Spiel button */
    public void goNewGame(View view) {
		Intent intent = new Intent(this, DisplayNewGameActivity.class);
		startActivity(intent);
	}

}
