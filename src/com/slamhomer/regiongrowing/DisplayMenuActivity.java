package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing.R;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DisplayMenuActivity extends Activity {
	private final Context context = this;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);
        
        final Button button1 = (Button)findViewById(R.id.button1);
        
        
        if (Gamemanager.getLocalPlayer().isInGame() == true) {
        	OnClickListener l=new OnClickListener() {
        		public void onClick(View v) {
        			final int REQUEST_CODE = 0;
        			if (Gamemanager.getLocalPlayer().isInGame() == true) {
        				startActivityForResult(new Intent(context, DisplayGame.class), REQUEST_CODE);
        			}else{
        				Messages.alert("Sie haben kein Spiel gestartet", context);
        			}
        	    }
        	};
        	
        	button1.setText("Weiter");
            button1.setOnClickListener(l);
        }else{
        	OnClickListener l=new OnClickListener() {
        		public void onClick(View v) {
        			final int REQUEST_CODE = 0;
        			startActivityForResult(new Intent(context, DisplayNewGameActivity.class), REQUEST_CODE);
        	    }
        	};
        	button1.setText("Neues Spiel");
        	button1.setOnClickListener(l);
        }
        
    }
	
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    // Stop method tracing that the activity started during onCreate()
	    android.os.Debug.stopMethodTracing();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    super.onActivityResult(requestCode, resultCode, intent);
	    final Button button1 = (Button)findViewById(R.id.button1);
	    if (requestCode == 0) {
	    	if (Gamemanager.getLocalPlayer().isInGame() == true) {
	        	OnClickListener l=new OnClickListener() {
	        		public void onClick(View v) {
	        			final int REQUEST_CODE = 0;
	        			if (Gamemanager.getLocalPlayer().isInGame() == true) {
	        				startActivityForResult(new Intent(context, DisplayGame.class), REQUEST_CODE);
	        			}else{
	        				Messages.alert("Sie haben kein Spiel gestartet", context);
	        			}
	        	    }
	        	};
	        	
	        	button1.setText("Weiter");
	            button1.setOnClickListener(l);
	        }else{
	        	OnClickListener l=new OnClickListener() {
	        		public void onClick(View v) {
	        			final int REQUEST_CODE = 0;
	        			startActivityForResult(new Intent(context, DisplayNewGameActivity.class), REQUEST_CODE);
	        	    }
	        	};
	        	button1.setText("Neues Spiel");
	        	button1.setOnClickListener(l);
	        }
	    }
	}
	
	/** Called when the user clicks the Exit button */
	public void goLogout(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
/*	TODO: Entweder Weiter oder Neues Spiel button
	/** Called when the user clicks the Weiter button 
    public void goContinue(View view) {
		if (Gamemanager.getLocalPlayer().isInGame() == true) {
			Intent intent = new Intent(this, DisplayGame.class);
			startActivity(intent);
		}else{
			Messages.alert("Sie haben kein Spiel gestartet", this);
		}
	}
    
    /** Called when the user clicks the Neues Spiel button 
    public void goNewGame(View view) {
		Intent intent = new Intent(this, DisplayNewGameActivity.class);
		startActivity(intent);
	}*/

