package com.slamhomer.regiongrowing;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.TurnTaskInThread;
import com.slamhomer.regiongrowing_network.UpdateThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayTask extends Activity {
	private static final int CAMERA_PIC_REQUEST = 1987;
	private static String titel = null;
	private static String desc = null;
	private static int inf;
	private static String erf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_task);
		
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("numbers");
		
		final TextView textView1 = (TextView)findViewById(R.id.textView1);
		titel = Gamemanager.getTask(position).getTaskName();
	    textView1.setText(titel);
	    final TextView textView2 = (TextView)findViewById(R.id.textView2);
	    desc = Gamemanager.getTask(position).getTaskDesc();
	    textView2.setText(desc);
	    final TextView textView4 = (TextView)findViewById(R.id.textView4);
	    inf = Gamemanager.getTask(position).getTaskInf();
	    textView4.setText(String.valueOf(inf));
	    final TextView textView6 = (TextView)findViewById(R.id.textView6);
	    erf = Gamemanager.getTask(position).getTaskErf();
	    textView6.setText(String.valueOf(erf));
	    
	    if(!erf.equals("Nicht abgeschlossen!")){
		    Button abgeben = (Button) findViewById(R.id.button1);
		    Button bild = (Button) findViewById(R.id.button2);
		    abgeben.setVisibility(View.GONE);
		    bild.setVisibility(View.GONE);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_task, menu);
		return true;
	}
	
	public void goTurnIn(View view){
		Thread turnIn = new TurnTaskInThread(titel);
		turnIn.run();
		try {
			turnIn.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread update = new UpdateThread(
				Gamemanager.getLocalPlayer().getName());
		update.run();
		try {
			update.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Messages.alert("Tasks wurde abgegeben.", "Erfolg!", "OK", this);

	}
	
	
	public void goCamera(View view){
	    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	    ImageView foto = (ImageView) findViewById(R.id.imageView1);
	    foto.setVisibility(View.VISIBLE);
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_PIC_REQUEST) {  
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
            ImageView image = (ImageView) findViewById(R.id.imageView1);  
            image.setImageBitmap(thumbnail);  
        }  
    }  

}
