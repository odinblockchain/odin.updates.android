package app.android.ignitioncoin.org.ignition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Activity send ignition coin from the app.
 */

public class SendIgnitionActivity extends AppCompatActivity {

    private Button mSend;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_ignition);

        mSend = (Button) findViewById(R.id.send);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Send");
        mToolbar.setTitle("Send");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent intent = new Intent(SendIgnitionActivity.this, MainActivity.class);
                // startActivity(intent);
                finish();

            }
        });

    }
}
