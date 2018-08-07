package app.android.ignitioncoin.org.ignition;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Create wallet Screen One
 */

public class CreateWallet extends AppCompatActivity {

    private LinearLayout mLin, mSeedView;
    private TextView mTitleCreateWallet, mDescriptionGenerateYourSeedText, mDescriptionClickGenerateSeed, mDescriptionVerifyGenerateSeed;
    private Button mGenerateSeed, mVerifySeed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_wallet);

        // Connect and Setup Views!
        setupViews();

        mGenerateSeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSeedView.setVisibility(View.VISIBLE);
                mDescriptionVerifyGenerateSeed.setVisibility(View.VISIBLE);
                mVerifySeed.setVisibility(View.VISIBLE);

            }
        });

        mVerifySeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreateWallet.this, VerifySeed.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }

    private void setupViews() {


        // Custom Font.
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/sofia-pro.ttf");

        mLin = (LinearLayout) findViewById(R.id.lin);

        mSeedView = (LinearLayout) findViewById(R.id.linear_seed_view);

        mGenerateSeed = (Button) findViewById(R.id.generate_seed);
        mVerifySeed = (Button) findViewById(R.id.verify_seed);

        mTitleCreateWallet = (TextView) findViewById(R.id.title_create_wallet);

        mDescriptionGenerateYourSeedText = (TextView) findViewById(R.id.description_generate_your_seed);
        mDescriptionClickGenerateSeed = (TextView) findViewById(R.id.description_click_to_generate_seed);
        mDescriptionVerifyGenerateSeed = (TextView) findViewById(R.id.description_verify_seed);

        mTitleCreateWallet.setTypeface(customFont);

        mDescriptionClickGenerateSeed.setTypeface(customFont);
        mDescriptionGenerateYourSeedText.setTypeface(customFont);
        mDescriptionVerifyGenerateSeed.setTypeface(customFont);

        mGenerateSeed.setTypeface(customFont);
        mVerifySeed.setTypeface(customFont);


    }
}
