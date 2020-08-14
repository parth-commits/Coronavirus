package com.example.android.coronavirus;

import android.content.Context;

import android.content.AsyncTaskLoader;

import java.util.List;

public class CountryLoader extends AsyncTaskLoader<List<countryObject>> {

    //query url
    private String mUrl;

    public CountryLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    /**
     * This is on a background thread.
     */
    @Override
    public List<countryObject> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<countryObject> countryObjects = QueryUtils.fetchCovidData(mUrl);
        return countryObjects;
    }
}
