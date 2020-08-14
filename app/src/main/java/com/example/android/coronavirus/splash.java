package com.example.android.coronavirus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends Activity {

    Animation rotateAnimation;
    ImageView logo;
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        logo = (ImageView) findViewById(R.id.logo1);
        rotateAnimation();
        /* New Handler to start the Menu-Activity
          and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.*/
                Intent mainIntent = new Intent(splash.this, MainActivity.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        logo.startAnimation(rotateAnimation);
    }

}
