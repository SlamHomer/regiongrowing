package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_network.LoginThread;
import com.slamhomer.regiongrowing_network.Network;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	/** Called when the user clicks the Login button */
	public void goLogin(View view) {
		if(gotInternet()==true){
			
			EditText name = (EditText) findViewById(R.id.editText1);
			EditText password = (EditText) findViewById(R.id.editText2);
			
			if (FormValidation.isLoginDataValid(name) == true &&
					FormValidation.isLoginDataValid(password) == true) {
							
				Thread loginthread = new LoginThread(name, password);
				loginthread.start();
				try {
					loginthread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String resultat = Network.getLastCode();
				if(!(resultat.equals("OK"))){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
					alertDialogBuilder.setTitle("Fehler");
					alertDialogBuilder
							.setMessage(resultat)
							.setCancelable(false)
							.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											return;
										}
									});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				}else{
				/*	// Erstes Update
					Thread updateThread = new UpdateThread(name.getText().toString());
					updateThread.start();
					try{
						updateThread.join();
					}catch (InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					Intent intent = new Intent(this,DisplayMenuActivity.class);
					startActivity(intent);
				}
			}else{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				alertDialogBuilder.setTitle("Fehler");
				alertDialogBuilder
						.setMessage("Ungueltiger Benutzername oder Passwort")
						.setCancelable(false)
						.setNeutralButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										return;
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		}
	}
	
	/** Called when the user clicks the Registrieren button */
	public void goReg(View view) {
		if(gotInternet()==true){
			Intent intent = new Intent(this, DisplayRegActivity.class);
			startActivity(intent);
		}
	}
	

	public void goOptions(View view) {
		Intent intent = new Intent(this, DisplayOptionsActivity.class);
		startActivity(intent);
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
    
    /*
     * TODO: In die Network Klasse
     */
	public boolean gotInternet() {
	    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    } else {
	        return false;
	    }
	}
}
