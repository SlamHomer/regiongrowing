package com.slamhomer.regiongrowing;

import android.app.Activity;
import android.content.Context;
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
			
			String res = "OK"; //Network.postDataLogin(name, password); //die richtige Anfrage
			
			if (res == "OK") {
				Intent intent = new Intent(this, DisplayMenuActivity.class);
				startActivity(intent);
			}else if(res == "FAIL"){
				/*
				 * TODO: Alert "Falscher Benutzername oder Password"
				 */
			}else{
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
			
			EditText name = (EditText)findViewById(R.id.loginname);
			EditText password = (EditText)findViewById(R.id.password);
			EditText email = (EditText)findViewById(R.id.email);
			
			String res = "OK"; //Network.postDataReg(name, password, email); //die richtige Anfrage
			
			if (res == "OK") {
				Intent intent = new Intent(this, DisplayRegActivity.class);
				startActivity(intent);
			}else if(res == "FAIL"){
				/*
				 * TODO: Alert "Bitte Ueberpruefen Sie Ihre Eingaben"
				 */
			}else{
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
