package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.Network;
import com.slamhomer.regiongrowing_network.NewGameThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DisplayNewGameActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_game);
    }
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	
	public void goNewGame(View view){
		EditText edittext = (EditText) findViewById(R.id.editText1);
		String text = edittext.getText().toString();
		int players;
		if ( text != null && !text.isEmpty()) {
			players = Integer.valueOf(text.toString());
		}else{
			return;
		}
		
		if(players > 1 && players < 7){
			Thread newGameThread = new NewGameThread(Gamemanager.getLocalPlayer().getName(), 
					players);
			newGameThread.start();
			try {
				newGameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String resultat = Network.getLastCode();
			if (!(resultat.equals("OK"))) {
				Messages.alert(resultat, this);
			}else{
				Thread updateThread = new UpdateThread(Gamemanager.getLocalPlayer().getName());
				updateThread.start();
				try {
					updateThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				alertDialogBuilder.setTitle("Spieler wurden zugeteilt");
				alertDialogBuilder
						.setMessage("Alles gut!")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										finish();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();	
			}
		}else{
			Messages.alert("Nur 2-6 Spieler möglich", this);
		}
		//Gamemanager.printAll();
	}
}