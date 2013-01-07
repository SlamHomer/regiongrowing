package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.GPS;
import com.slamhomer.regiongrowing_network.LoginThread;
import com.slamhomer.regiongrowing_network.Network;
import com.slamhomer.regiongrowing_network.SetHomeThread;
import com.slamhomer.regiongrowing_network.UpdateThread;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	/** Called when the user clicks the Login button */
	public void goLogin(View view) {

		if (Network.gotInternet(this)==true) {
			if (GPS.loadHomeLoc(this) == true) {

				EditText name = (EditText) findViewById(R.id.editText1);
				EditText password = (EditText) findViewById(R.id.editText2);

				if (FormValidation.isLoginDataValid(name) == true
						&& FormValidation.isLoginDataValid(password) == true) {

					Thread loginthread = new LoginThread(name, password);
					loginthread.start();
					try {
						loginthread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String resultat = Network.getLastCode();
					if (!(resultat.equals("OK"))) {
						Messages.alert(resultat, this);
					} else {

						// Erstes Update
						// TODO: Besser lösen
						//#################################
						Thread updateThread = new UpdateThread(name.getText()
								.toString());
						updateThread.start();
						try {
							updateThread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (Gamemanager.getLocalPlayer().isInGame() == false) {
							Thread home = new SetHomeThread();
							home.start();
							try {
								home.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						Thread updateThread2 = new UpdateThread(name.getText()
								.toString());
						updateThread2.start();
						try {
							updateThread2.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						// ##############################

						Gamemanager.printAll();

						Intent intent = new Intent(this,
								DisplayMenuActivity.class);
						startActivity(intent);
					}
				} else {
					Messages.alert("Ungueltiger Benutzername oder Passwort", this);
				}
			} else {
				Messages.alert("Sie haben Ihre Home Location noch nicht gesetzt", this);
			}
		}
	}
	
	/** Called when the user clicks the Registrieren button */
	public void goReg(View view) {
		if(Network.gotInternet(this)==true){
			Intent intent = new Intent(this, DisplayRegActivity.class);
			startActivity(intent);
		}
	}
	

	public void goOptions(View view) {
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(!enabled){
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
			alertDialogBuilder.setTitle("Fehler");
			alertDialogBuilder
					.setMessage("GPS ist ausgeschaltet. GPS anschalten?")
					.setCancelable(false)
					.setPositiveButton("Ja",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(intent);
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
		}else{
			Intent intent = new Intent(this, DisplayOptionsActivity.class);
			startActivity(intent);
		}
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
