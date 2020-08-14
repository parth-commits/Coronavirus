package com.example.android.coronavirus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<countryObject>> {
    private static CovidAdapter adapter;
    public static final String LOG_TAG = MainActivity.class.getName();
    private TextView mEmptyStateTextView;
    private ImageView mNoNet;
    private static final int MAIN_LOADER_ID = 1;
    private String mUrl = "https://corona.lmao.ninja/v2/countries/Canada,India,USA,Brazil,Russia,ZA,NZ,China,Australia,UAE,Mexico,NewZealand?yesterday";

    SwipeRefreshLayout swipeRefreshLayout;
    public static CovidAdapter getAdapter(){
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        // Find a reference to the {@link ListView} in the layout
        ListView CountriesListView = (ListView) findViewById(R.id.list);

        adapter = new CovidAdapter(this, new ArrayList<countryObject>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        CountriesListView.setAdapter(adapter);
        CountriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                countryObject currentCountry = adapter.getItem(position);
                countryinfo info = new countryinfo();

                //Create a new intent to open the {@link NumbersActivity}
                Intent countryIntent = new Intent(MainActivity.this, countryinfo.class);
                countryIntent.putExtra("country_pos", position);
                // Send the intent to launch a new activity
                startActivity(countryIntent);
            }
        });


        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        CountriesListView.setEmptyView(mEmptyStateTextView);

        mNoNet = (ImageView) findViewById(R.id.nonet);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(MAIN_LOADER_ID, null,this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mNoNet.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            // Update empty state with no connection error message
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                //your code on swipe refresh
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    // Get a reference to the LoaderManager, in order to interact with loaders.
                    mNoNet.setVisibility(View.GONE);
                    LoaderManager loaderManager = getLoaderManager();

                    // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                    // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                    // because this activity implements the LoaderCallbacks interface).
                    loaderManager.initLoader(MAIN_LOADER_ID, null, MainActivity.this);
                } else {
                    // Otherwise, display error
                    // First, hide loading indicator so error message will be visible
                    adapter.clear();
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
                    mNoNet.setVisibility(View.VISIBLE);
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                    // Update empty state with no connection error message
                }
            }
        });
    }

    @Override
    public Loader<List<countryObject>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new CountryLoader(this, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<countryObject>> loader, List<countryObject> data) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);
        // Clear the adapter of previous earthquake data
        adapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<countryObject>> loader) {
        // Loader reset, so we can clear out our existing data.
        adapter.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Settings" item, show the app settings UI...
                Intent countryIntent = new Intent(MainActivity.this, about.class);
                // Send the intent to launch a new activity
                startActivity(countryIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.creditbutton, menu);
        return super.onCreateOptionsMenu(menu);
    }
}