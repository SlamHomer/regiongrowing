package com.slamhomer.regiongrowing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Editable;
import android.widget.EditText;

public class FormValidation {

	public static boolean isEmailValid(EditText email)
    {
		Editable tmp = email.getText();
		String val_email = tmp.toString();
		
       final String regExpn =
             "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                 +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                   +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                   +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                   +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                   +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

     CharSequence inputStr = val_email;

     Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
     Matcher matcher = pattern.matcher(inputStr);

     if(matcher.matches())
        return true;
     else
        return false;
    }
	
	/*
	 * Methode die Benutzername und Passwort auf Korrektheit pr�ft.
	 * Korrekte Benutzernamen und Passw�rter sind:
	 * nur a-z, A-Z, 0-9 sind erlaubt
	 * muss zwischen 5 und 20 Zeichen haben
	 * 
	 * TODO: Ausf�hrlich testen
	 */
	protected static boolean isLoginDataValid(EditText input){
		Editable tmp = input.getText();
		String val_input = tmp.toString();
		
		final String regExpn =
	    		"([a-zA-Z0-9_]{5,20})"; // nur [a-zA-Z0-9_] und 5 bis 20 Zeichen

	     CharSequence inputStr = val_input;

	     Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
	     Matcher matcher = pattern.matcher(inputStr);

	     if(matcher.matches())
	        return true;
	     else
	        return false;
	    }
	
}
