package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
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
			
			EditText name  = (EditText)findViewById(R.id.editText1);		
			EditText password  = (EditText)findViewById(R.id.editText2);
			
			String res = Network.postDataLogin(name, password); //die richtige Anfrage
			
			if (res == "OK") {
				Intent intent = new Intent(this, DisplayMenuActivity.class);
				startActivity(intent);
			}else if(res == "FAIL"){
				/*
				 * TODO: Alert "Falscher Benutzername oder Password"
				 */
			}else{
				System.out.println("RES: "+res);
				/*
				 * TODO: Alert "Kritischer Fehler"
				 */
			}
		}else{
			/*
			 * TODO: Alert "Keine Internet Verbindung"
			 */
		}
	}
	
	/** Called when the user clicks the Registrieren button */
	public void goReg(View view) {
		if(gotInternet()==true){
			Intent intent = new Intent(this, DisplayRegActivity.class);
			startActivity(intent);
			
		}else{
			/*
			 * TODO: Alert "Keine Internet Verbindung"
			 */
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
