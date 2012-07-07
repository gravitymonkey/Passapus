package com.fingerengines.passapus;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.ClipboardManager;

public class PasswordActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        final Button button = (Button) findViewById(R.id.button1);
        final EditText domainEditText = (EditText) findViewById(R.id.editText1);
        final EditText lengthEditText = (EditText) findViewById(R.id.editText2);
        final EditText secretEditText = (EditText) findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String domain = domainEditText.getText().toString();
            	int length = 0;
            	if (lengthEditText.getText() != null){
            		String lengthString = lengthEditText.getText().toString();
            		if (lengthString != null){
            			if (lengthString.trim().length() > 0){
            				try {
            					length = Integer.parseInt(lengthString);
            				} catch (Exception e){
            					length = 0;
            				}
            			}
            		}
            	}
            	String secret = secretEditText.getText().toString();
            	boolean fail = false;
            	if (domain == null || secret == null){
            		fail = true;
            	} else {
            		if (domain.trim().length() == 0){
            			fail = true;
            		}
            		if (secret.trim().length() == 0){
            			fail = true;
            		}
            	}
            	
            	if (fail){
            		Toast.makeText(getApplicationContext(), "Missing domain and/or secret password?", Toast.LENGTH_LONG).show();
            	} else {
            		String o = null;
            		try {
						o = AeSimpleSHA1.SHA1(domain + " " + secret);
						if (length > 0){
							if (length < o.length()){
								o = o.substring(0, length);
							}
						}
						ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
						clipboard.setText(o);
						Toast.makeText(getApplicationContext(), "Your Password is Ready To Paste", Toast.LENGTH_SHORT).show();
					} catch (NoSuchAlgorithmException e) {
						Toast.makeText(getApplicationContext(), "Uhoh: " + e.toString(), Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						Toast.makeText(getApplicationContext(), "Uhoh: " + e.toString(), Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
            	}
            	
            }
        });        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_password, menu);
        Intent intent1 = new Intent(this, HelpActivity.class);
        menu.getItem(0).setIntent(intent1);
        return true;
    }
    
    
}
