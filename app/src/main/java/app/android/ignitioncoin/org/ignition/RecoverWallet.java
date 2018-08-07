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
 * Recover wallet for someone who has already created a wallet.
 */

public class RecoverWallet extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "Preferences";
    private static final String HAS_CREATED_OR_RECOVERED_WALLET = "HasCreatedOrRecoveredWallet";

    private TextView mTitle, mDescriptionRecoverWallet;
    private EditText mSeed;
    private Button mRecoverWallet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recover_wallet);

        setupViews();

        mRecoverWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seed = mSeed.getText().toString().trim();

                if (seed.isEmpty()) {

                    Toast.makeText(RecoverWallet.this, "Please enter your seed.", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(RecoverWallet.this, "Yay! Verification Successful. ", Toast.LENGTH_SHORT).show();

                    // Update Viewed in Shared Preferences!
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(HAS_CREATED_OR_RECOVERED_WALLET, true);
                    editor.commit();

                    Intent intent = new Intent(RecoverWallet.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }
        });


    }

    private void setupViews() {

        // Custom Font.
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/sofia-pro.ttf");

        mTitle = (TextView) findViewById(R.id.title_verify_seed);
        mDescriptionRecoverWallet = (TextView) findViewById(R.id.description_recover_wallet);

        mSeed = (EditText) findViewById(R.id.edit_text_seed);

        mRecoverWallet = (Button) findViewById(R.id.recover_wallet);

        mTitle.setTypeface(customFont);
        mDescriptionRecoverWallet.setTypeface(customFont);

        mSeed.setTypeface(customFont);

        mRecoverWallet.setTypeface(customFont);


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RecoverWallet.this, RecoverOrCreateWallet.class);
        startActivity(intent);
    }
}
