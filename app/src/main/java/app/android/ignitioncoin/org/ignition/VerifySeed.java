package app.android.ignitioncoin.org.ignition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Verify the seed your have generated here.
 */

public class VerifySeed extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "Preferences";
    private static final String HAS_CREATED_OR_RECOVERED_WALLET = "HasCreatedOrRecoveredWallet";

    private TextView mTitle, mDescriptionVerifySeed;
    private EditText mSeed;
    private Button mVerifySeed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_seed);

        setupViews();

        mVerifySeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seed = mSeed.getText().toString().trim();

                if (seed.isEmpty()) {

                    Toast.makeText(VerifySeed.this, "Please enter your seed.", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(VerifySeed.this, "Yay! Verification Successful. ", Toast.LENGTH_SHORT).show();

                    // Update Viewed in Shared Preferences!
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(HAS_CREATED_OR_RECOVERED_WALLET, true);
                    editor.commit();

                    Intent intent = new Intent(VerifySeed.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        });

    }

    private void setupViews() {

        // Custom Font.
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/sofia-pro.ttf");

        mTitle = (TextView) findViewById(R.id.title_verify_seed);
        mDescriptionVerifySeed = (TextView) findViewById(R.id.description_verify_seed);

        mSeed = (EditText) findViewById(R.id.edit_text_seed);

        mVerifySeed = (Button) findViewById(R.id.verify_seed);

        mTitle.setTypeface(customFont);
        mDescriptionVerifySeed.setTypeface(customFont);

        mSeed.setTypeface(customFont);

        mVerifySeed.setTypeface(customFont);


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VerifySeed.this, RecoverOrCreateWallet.class);
        startActivity(intent);
    }
}
