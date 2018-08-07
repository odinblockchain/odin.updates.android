package app.android.ignitioncoin.org.ignition;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Activity to receive ignition coin from someone else. Use would be able to see and copy their address and send that to a person to receive ignition coin.
 */

public class ReceiveIgnitionActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mBack, mCopyToClipboard, mShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_ignition_coin);

        mBack = (Button) findViewById(R.id.back);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mCopyToClipboard = (Button) findViewById(R.id.copy_to_clipboard);
        mShare = (Button) findViewById(R.id.share);

        mCopyToClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("address", "iUBcGBYNEkqBe2hkDae2qoJKWJB2fkxNTh");
                clipboard.setPrimaryClip(clip);

                Toast.makeText(ReceiveIgnitionActivity.this, "Successfully Copied to clipboard.", Toast.LENGTH_SHORT).show();

            }
        });

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    // i.putExtra(Intent.EXTRA_SUBJECT, "Send Ignition to this address");
                    String sAux = "Send Ignition to the following address - iUBcGBYNEkqBe2hkDae2qoJKWJB2fkxNTh";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, getString(R.string.share)));
                } catch (Exception e) {
                    e.toString();
                }

            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Send");
        mToolbar.setTitle("Send");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ReceiveIgnitionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
