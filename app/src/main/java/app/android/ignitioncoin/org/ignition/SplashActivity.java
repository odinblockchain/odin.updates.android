package app.android.ignitioncoin.org.ignition;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * First screen of the app. Will load for couple of seconds with a small animations.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView mBackgroundImage;
    long time  = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mBackgroundImage = (ImageView) findViewById(R.id.background);

        // Fade Animation for the Background!
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade);
        anim.reset();
        mBackgroundImage.clearAnimation();
        mBackgroundImage.startAnimation(anim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Create an intent that will start the main activity.
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);

                //Finish splash activity so user cant go back to it.
                SplashActivity.this.finish();
                overridePendingTransition(0, R.anim.splash_fade_new);

                //Apply splash exit (fade out) and main entry (fade in) animation transitions.
                // overridePendingTransition(R.anim.mainfadein, R.anim.splashfadeout);
            }
        }, time);

    }

}
