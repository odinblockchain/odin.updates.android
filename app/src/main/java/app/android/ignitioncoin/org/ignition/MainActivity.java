package app.android.ignitioncoin.org.ignition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.CoinMarketCapApi;
import onboarding.AppOnboarding;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Home Screen of the App!
 */


public class MainActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "Preferences";
    private static final String HAS_WATCHED_TUTORIAL = "HasWatchedTutorial";
    private static final String HAS_CREATED_OR_RECOVERED_WALLET = "HasCreatedOrRecoveredWallet";

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView mIgnitionCoinPrice, mPriceOfIgnitionInBitcoin;
    private Toolbar toolbar;

    private Button mSend, mReceive;

    double bitcoinPrice = 0.0;

    double ignitionPrice = 0.0;
    double ignitionPriceInBitcoin = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        // To Set Custom Font.
        // Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/sofia-pro.ttf");

        mSend = (Button) findViewById(R.id.send);
        mReceive = (Button) findViewById(R.id.receive);

        mIgnitionCoinPrice = (TextView) findViewById(R.id.ignition_price);
        mPriceOfIgnitionInBitcoin = (TextView) findViewById(R.id.price_in_bitcoin);

        getBitcoinPrice();


        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SendIgnitionActivity.class);
                startActivity(intent);

            }
        });

        mReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ReceiveIgnitionActivity.class);
                startActivity(intent);


            }
        });

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean hasWatchedTutorial = prefs.getBoolean(HAS_WATCHED_TUTORIAL, false);
        if (!hasWatchedTutorial) {
            Intent intent = new Intent(MainActivity.this, AppOnboarding.class);
            startActivity(intent);
            finish();
        } else {

            // TODO: Check if user is logged in or not

            boolean hasCreatedOrReceovedWallet = prefs.getBoolean(HAS_CREATED_OR_RECOVERED_WALLET, false);

            if (!hasCreatedOrReceovedWallet) {

                Intent intent = new Intent(MainActivity.this, RecoverOrCreateWallet.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.home:
                        Toast.makeText(MainActivity.this, "To Home!", Toast.LENGTH_SHORT).show();


                    default:
                        Toast.makeText(getApplicationContext(), "Testing!", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.home);

//        Menu m = navigationView.getMenu();
//        for (int i=0;i<m.size();i++) {
//            MenuItem mi = m.getItem(i);
//
//            //for aapplying a font to subMenu ...
//            SubMenu subMenu = mi.getSubMenu();
//            if (subMenu!=null && subMenu.size() >0 ) {
//                for (int j=0; j <subMenu.size();j++) {
//                    MenuItem subMenuItem = subMenu.getItem(j);
//                    applyFontToMenuItem(subMenuItem);
//                }
//            }
//
//            //the method we have create in activity
//            applyFontToMenuItem(mi);
//        }

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    /**
     *  Get Bitcoin Price in USD from coinmarketcap.com
     *  This fetching happens everytime user opens the app and is updated internally.
     */
    private void getBitcoinPrice() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<CoinMarketCapApi>> call = apiService.getCoinPrice("bitcoin");

        call.enqueue(new Callback<List<CoinMarketCapApi>>() {
            @Override
            public void onResponse(Call<List<CoinMarketCapApi>> call, Response<List<CoinMarketCapApi>> response) {

                try {

                    // Parsing JSON received from the API Call.
                    List<CoinMarketCapApi> bitCoinPrice = response.body();

                    // Getting values of bitcoin in Double
                    String priceOfBitcoin = response.body().get(0).getPriceUsd();
                    bitcoinPrice = Double.parseDouble(priceOfBitcoin);

                    getIgnitionPrice();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, "Error Getting Bitcoin Price", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<CoinMarketCapApi>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error Retrieving Bitcoin Price! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     *  Get Bitcoin Price in USD from coinmarketcap.com
     *  This fetching happens everytime user opens the app and is updated internally.
     */
    private void getIgnitionPrice() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<CoinMarketCapApi>> call = apiService.getCoinPrice("ignition");

        call.enqueue(new Callback<List<CoinMarketCapApi>>() {
            @Override
            public void onResponse(Call<List<CoinMarketCapApi>> call, Response<List<CoinMarketCapApi>> response) {

                try {

                    // Parsing JSON received from the API Call.
                    List<CoinMarketCapApi> bitCoinPrice = response.body();

                    // Getting values of bitcoin in Double
                    String priceOfIgnition = response.body().get(0).getPriceUsd();
                    ignitionPrice = Double.parseDouble(priceOfIgnition);

                    String priceOfIgnitionInBtc = response.body().get(0).getPriceBtc();
                    ignitionPriceInBitcoin = Double.parseDouble(priceOfIgnitionInBtc);

                    mIgnitionCoinPrice.setText("$" + getFormattedPriceto3DP(ignitionPrice));
                    mPriceOfIgnitionInBitcoin.setText(getFormattedPriceTo5DP(ignitionPriceInBitcoin) + " Btc");


                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, "Error Getting Bitcoin Price", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<CoinMarketCapApi>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error Retrieving Bitcoin Price! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    // Format the price to 3 decimal places.
    private String getFormattedPriceto3DP(Double price) {
        return String.format("%.3f", price);
    }

    // Format the price to 4 decimal places.
    private String getFormattedPriceTo5DP(Double price) {
        return String.format("%.5f", price);
    }

}


