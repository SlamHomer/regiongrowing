package com.slamhomer.regiongrowing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class Messages {
	public static void alert(String msg, Context context){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Fehler");
		alertDialogBuilder
				.setMessage(msg)
				.setCancelable(false)
				.setNeutralButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog, int id) {
								return;
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	public static void alert(String msg, String titel, String button, Context context){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(titel);
		alertDialogBuilder
				.setMessage(msg)
				.setCancelable(false)
				.setNeutralButton(button,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog, int id) {
								return;
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
}
