package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.LoginThread;
import com.slamhomer.regiongrowing_network.Network;
import com.slamhomer.regiongrowing_network.NewGameThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;

public class DisplayNewGameActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_new_game);
    }
	
	public void goNewGame(View view){
		EditText text = (EditText) findViewById(R.id.editText1);
		int players = Integer.valueOf(text.getText().toString());
		
		if(players < 7){
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
				ErrorMsg.alert(resultat, this);
			}else{
				//TODO: Ladebalken oder sowas mit "bitte warten"
				ErrorMsg.alert("Spieler wurden zugeteilt", "Alles gut!", 
						"OK", this);
				Thread updateThread = new UpdateThread(Gamemanager.getLocalPlayer().getName());
				updateThread.start();
				try {
					updateThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else{
			ErrorMsg.alert("Nur 2-6 Spieler möglich", this);
		}
		Gamemanager.printAll();
	}
}