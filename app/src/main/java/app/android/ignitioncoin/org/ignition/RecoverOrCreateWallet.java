package app.android.ignitioncoin.org.ignition;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This activity is shown to users initially so they could create their own wallet or if they already have a wallet created then they can use
 * their existing seeds to recover their wallet.
 */

public class RecoverOrCreateWallet extends AppCompatActivity {

    private TextView mAbout;
    private Button mCreateWallet, mRecoverWallet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recover_or_create_wallet);

        // Custom Font.
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/sofia-pro.ttf");

        mAbout = (TextView) findViewById(R.id.about_ignition);

        mCreateWallet = (Button) findViewById(R.id.create_wallet);
        mRecoverWallet = (Button) findViewById(R.id.recover_wallet);

        mAbout.setTypeface(customFont);

        mRecoverWallet.setTypeface(customFont);
        mCreateWallet.setTypeface(customFont);


        // Go to Create Wallet Screen!
        mCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecoverOrCreateWallet.this, CreateWallet.class);
                startActivity(intent);

            }
        });

        // Go to Recover Wallet Screen!
        mRecoverWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecoverOrCreateWallet.this, RecoverWallet.class);
                startActivity(intent);


            }
        });


    }
}
