package app.android.blockchain.org.odin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import onboarding.AppOnboarding;


/**
 * Home Screen of the App!
 */
public class MainActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "Preferences";
    private static final String HAS_WATCHED_TUTORIAL = "HasWatchedTutorial";
    // private static final String HAS_CREATED_OR_RECOVERED_WALLET = "HasCreatedOrRecoveredWallet";

    private TextView mDescription;
    private Button mWebsite, mTwitter, mDiscord, mWhitepaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/rubiclight.ttf");

        mDescription = (TextView) findViewById(R.id.description);
        mWebsite = (Button) findViewById(R.id.button_website);
        mTwitter = (Button) findViewById(R.id.button_twitter);
        mDiscord = (Button) findViewById(R.id.button_discord);
        mWhitepaper = (Button) findViewById(R.id.button_whitepaper);

        mDescription.setTypeface(customFont);
        mWebsite.setTypeface(customFont);
        mTwitter.setTypeface(customFont);
        mDiscord.setTypeface(customFont);

        mWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://odinblockchain.org/"));
                    startActivity(intent);
                } catch (Exception e) {

                    // 1. Set Url to website url using singleton
                    MyApplication.getInstance().setUrl("https://odinblockchain.org/");

                    // 2. Go to URL Activity.
                    Intent websiteIntent = new Intent(MainActivity.this, OpenUrlActivity.class);
                    startActivity(websiteIntent);
                }

            }
        });

        mDiscord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://discord.gg/D5SFHy4"));
                    startActivity(intent);
                } catch (Exception e) {

                    // 1. Set Url to whitepaper url using singleton
                    MyApplication.getInstance().setUrl("https://discord.gg/D5SFHy4");

                    // 2. Go to URL Activity.
                    Intent websiteIntent = new Intent(MainActivity.this, OpenUrlActivity.class);
                    startActivity(websiteIntent);
                }

            }
        });


        mTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/odinblockchain"));
                    startActivity(intent);
                } catch (Exception e) {

                    // 1. Set Url to whitepaper url using singleton
                    MyApplication.getInstance().setUrl("https://twitter.com/odinblockchain");

                    // 2. Go to URL Activity.
                    Intent websiteIntent = new Intent(MainActivity.this, OpenUrlActivity.class);
                    startActivity(websiteIntent);
                }

            }
        });

        mWhitepaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://odinblockchain.org/odin-blockchain-white-paper/"));// Uri.parse("https://odin.ly/2OUCTVf"));
                    startActivity(intent);
                } catch (Exception e) {

                    // 1. Set Url to whitepaper url using singleton
                    MyApplication.getInstance().setUrl("https://odinblockchain.org/odin-blockchain-white-paper/");
                    // MyApplication.getInstance().setUrl("https://odin.ly/2OUCTVf");

                    // 2. Go to URL Activity.
                    Intent websiteIntent = new Intent(MainActivity.this, OpenUrlActivity.class);
                    startActivity(websiteIntent);
                }

            }
        });

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean hasWatchedTutorial = prefs.getBoolean(HAS_WATCHED_TUTORIAL, false);
        if (!hasWatchedTutorial) {
            Intent intent = new Intent(MainActivity.this, AppOnboarding.class);
            startActivity(intent);
            finish();
        }


    } // End of OnCreate Method.

} // End of Main Activity.
