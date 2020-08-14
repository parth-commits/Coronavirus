package com.example.android.coronavirus;

import android.app.Activity;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class about extends Activity {


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.about);

        TextView note = (TextView) findViewById(R.id.note);
        TextView info = (TextView) findViewById(R.id.info);

        ImageView logo = (ImageView) findViewById(R.id.logo);

        logo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Create a new intent to open the {@link NumbersActivity}
                Intent emailintent = new Intent(Intent.ACTION_SENDTO);
                emailintent.setData(Uri.parse("mailto:parthpatel1087@gmail.com"));//only email apps handle this
                //Start the new activity
                if (emailintent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailintent);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            note.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            info.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

}
