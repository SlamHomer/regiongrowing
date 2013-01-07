package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.LeaveGameThread;

import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

public class DisplayMenuActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);
    }
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	
	
	/** Called when the user clicks the Exit button */
	public void goLogout(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	/** Called when the user clicks the Weiter button */
    public void goContinue(View view) {
		if (Gamemanager.getLocalPlayer().isInGame() == true) {
			Intent intent = new Intent(this, DisplayGame.class);
			startActivity(intent);
		}else{
			Messages.alert("Sie haben kein Spiel gestartet", this);
		}
	}
    
    /** Called when the user clicks the Neues Spiel button */
    public void goNewGame(View view) {
		Intent intent = new Intent(this, DisplayNewGameActivity.class);
		startActivity(intent);
	}
    
    /** Called when the user clicks the Verlassen button */
    public void goLeave(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Achtung!");
		alertDialogBuilder
				.setMessage("Wollen Sie wirklich das aktuelle Spiel verlassen?")
				.setCancelable(false)
				.setPositiveButton("Ja",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
						    	LeaveGameThread lg = new LeaveGameThread();
								lg.run();
								try {
									lg.join();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						})
				.setNegativeButton("Nein",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							return;
						}
					});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();	
	}
}
