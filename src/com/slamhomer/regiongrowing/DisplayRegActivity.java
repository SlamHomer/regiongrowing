package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_network.Network;
import com.slamhomer.regiongrowing_network.RegThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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

		
		EditText name = (EditText)findViewById(R.id.loginname);
		EditText password = (EditText)findViewById(R.id.password);
		EditText email = (EditText)findViewById(R.id.email);
		
		if (FormValidation.isEmailValid(email) == true &&
				FormValidation.isLoginDataValid(name) == true &&
				FormValidation.isLoginDataValid(password) == true) {
			//Context context = this;
			//Network.postDataReg(name, password, email, context);
			
			Thread regthread = new RegThread(name, password, email);
			regthread.start();
			try {
				regthread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("AFTER TRHEAD RES: "+Network.getLastCode());
		
		// DIES MUSS IN EINE IF ANFRAGE EINGEBAUT WERDEN
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		
		
		/*if(res=0){ //bis wir eine rueckgabe haben
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DisplayRegActivity.this);
			alertDialogBuilder.setTitle("Fehler");
			alertDialogBuilder
					.setMessage("Registrierung fehlgeschlagen")
					.setCancelable(false)
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									return;
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}*/
		
	}
}
