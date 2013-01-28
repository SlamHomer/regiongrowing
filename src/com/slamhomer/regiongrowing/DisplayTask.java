package com.slamhomer.regiongrowing;

import java.io.ByteArrayOutputStream;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_network.GetImgThread;
import com.slamhomer.regiongrowing_network.TurnTaskInThread;
import com.slamhomer.regiongrowing_network.UpdateThread;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//TODO: Unschön alles, warte auf Skript fix
public class DisplayTask extends Activity {
	private static String titel = null;
	private static String desc = null;
	private static int inf;
	private static String erf;
	
	private static final int CAMERA_PIC_REQUEST = 1987;
	private static boolean tookPic = false;
	private static String imgCode = null;
	
	private static final String debugString = "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";
			

	
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
		    Button anzeigen = (Button) findViewById(R.id.button3);
		    abgeben.setVisibility(View.GONE);
		    bild.setVisibility(View.GONE);
		    anzeigen.setVisibility(View.VISIBLE);
	    }
	    
	    tookPic = false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_task, menu);
		return true;
	}
	
	public void goTurnIn(View view){
		
		if(tookPic == true){
			System.out.println("Mit imgCode!");
			Thread turnIn = new TurnTaskInThread(titel,imgCode);
			turnIn.run();
			try {
				turnIn.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			Thread turnIn = new TurnTaskInThread(titel,null);
			turnIn.run();
			try {
				turnIn.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
		Thread update = new UpdateThread(
				Gamemanager.getLocalPlayer().getName());
		update.run();
		try {
			update.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//TODO: auf fehler überprüfen
		Messages.alert("Tasks wurde abgegeben.", "Erfolg!", "OK", this);
		tookPic = false;
		
		Button abgeben = (Button) findViewById(R.id.button1);
	    Button bild = (Button) findViewById(R.id.button2);
	    Button anzeigen = (Button) findViewById(R.id.button3);
	    abgeben.setVisibility(View.GONE);
	    bild.setVisibility(View.GONE);
	    anzeigen.setVisibility(View.VISIBLE);
	}
	
	
	public void goCamera(View view){
	    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
/*	    ImageView foto = (ImageView) findViewById(R.id.imageView1);
	    foto.setVisibility(View.VISIBLE);*/
	}
	
	public void goShowImg(View view){
		ImageView image = (ImageView) findViewById(R.id.imageView1);
		
		int pos = Gamemanager.getPosFromTask(titel);
		
		Thread getImg = new GetImgThread(titel);
		getImg.run();
		try {
			getImg.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String tmp = Gamemanager.getTask(pos).getImg();
		System.out.println("TASK IMG: "+tmp);
		//Bitmap bm = stringToBitmap(tmp);
		
		//DEBUG
		Bitmap bm = stringToBitmap(debugString);
		
		image.setImageBitmap(bm);
		image.setVisibility(View.VISIBLE);
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAMERA_PIC_REQUEST) {  
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
            
/*            ImageView image = (ImageView) findViewById(R.id.imageView1);  
            //image.setImageBitmap(thumbnail); //direkt das bild anzeigen
            
            System.out.println("#########################");
            System.out.println("IMAGE BYTE CODE: "+getImageByte(thumbnail));
            System.out.println("#########################");
            
            //bild zu byte-> byte zu bild und anzeigen
            byte[] b = getImageByte(thumbnail);
            Bitmap bit = getByteImage(b);
            image.setImageBitmap(bit);*/
            
            String s = bitmapToString(thumbnail);
            imgCode = s;
            tookPic = true;
            
            System.out.println("#########################");
            System.out.println("IMAGE STRING CODE: "+s);
            System.out.println("#########################");
            
        }  
    }
    
    
    
    private String bitmapToString(Bitmap bm){
/*    	Resources r = this.getResources();
        Bitmap bm = BitmapFactory.decodeResource(r, R.drawable.logo);
        */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object   
        byte[] b = baos.toByteArray();
        //String encodedImage = Base64.encode(b, Base64.DEFAULT);
        String encodedImage = Base64.encodeToString(b,Base64.DEFAULT);
        
        return encodedImage;
    }
    
    private Bitmap stringToBitmap(String string){
    	byte[] decodedString = Base64.decode(string, Base64.DEFAULT);
    	Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    	
    	return decodedByte;
    }
}
