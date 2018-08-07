package onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

import app.android.ignitioncoin.org.ignition.MainActivity;

/**
 * App Onboarding Screen!
 */

public class AppOnboarding extends AppIntro {

    private static final String MY_PREFS_NAME = "Preferences";
    private static final String HAS_WATCHED_TUTORIAL = "HasWatchedTutorial";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.

        SlideOne s1 = new SlideOne();
        SlideTwo s2 = new SlideTwo();
        SlideThree s3 = new SlideThree();
        SlideFour s4 = new SlideFour();

        addSlide(s1);
        addSlide(s2);
        addSlide(s3);
        addSlide(s4);

        // OPTIONAL METHODS
        // Override bar/separator color.
        // setBarColor(Color.parseColor("#3F51B5"));
         setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        // Update Viewed in Shared Preferences!
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(HAS_WATCHED_TUTORIAL, true);
        editor.commit();

        // Go to Home screen!
        startActivity(new Intent(AppOnboarding.this, MainActivity.class));
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}
