package com.fingerengines.passapus;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.TextView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;

@SuppressLint("ParserError") 
public class HelpActivity extends Activity {
/*
<b>Domain</b>\nEnter in the domain name of the site, like \"gmail.com\" or \"facebook.com\".\n\n<b>Password Length</b>\nNot all sites will allow you to add a really long password.  For those that don't, you can specify a length and the password passed to the paste buffer will be trimmed accordingly.  Leave it blank or at 0 to use the full-length hashed password.\n\n<b>Secret</B>\nEnter in your universal password.\n\n
 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        final TextView tv = (TextView) findViewById(R.id.helpTextView);
        String myText = getString(R.string.copy);
        tv.setText(Html.fromHtml("<b>" + myText + "</b>").toString());
        tv.setMovementMethod(new ScrollingMovementMethod());
    }


    

}
