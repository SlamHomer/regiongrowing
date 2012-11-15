package com.slamhomer.regiongrowing;

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
		
		String res = Network.postDataReg(name, password, email); 
		
		System.out.println("RES: "+res);
		
		if (res == "OK") {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			// Frage hier nicht nach FAIL sondern mach einfach else... Ich werde versuchen den Fehler zuruckzugeben und der kann dann in der Alertbox ausgegeben werden!
		}else if(res == "FAIL"){
			/*
			 * TODO: Alert "Bitte Ueberpruefen Sie Ihre Eingaben"
			 */
		}else{
			/*
			 * TODO: Alert "Kritischer Fehler"
			 */
		}
		
	}
}
