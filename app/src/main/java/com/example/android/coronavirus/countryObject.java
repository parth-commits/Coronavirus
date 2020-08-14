package com.example.android.coronavirus;

import android.graphics.Bitmap;
import java.text.NumberFormat;

public class countryObject {

    //name of the country
    private String mCountryName;

    //last updated time
    private long mTimeInMilliseconds;

    //total cases
    private int mTotalCases;

    //total deaths
    private int mDeaths;

    //recovered
    private int mRecovered;

    //active
    private int mActive;

    //critical
    private int mCritical;

    //population
    private int mPop;

    //flag url
    private  String mFlagurl;

    //todaycases
    private int mtodayCases;

    //todaydeaths
    private int mtodayDeaths;

    //todayrecovered
    private int mtodayRecovered;

    //bitmap of flag
    private Bitmap mflag;

    //Number formatter
    private NumberFormat myFormat = NumberFormat.getInstance();

    //initializer
    public countryObject(String countryName, long timeInMilliseconds, int totalCases, int deaths, int recovered, int active, int critical, int pop, String flagurl, int todaycases, int todaydeaths, int todayrecovered, Bitmap flag){
        mCountryName = countryName;
        mTimeInMilliseconds = timeInMilliseconds;
        mTotalCases = totalCases;
        mDeaths = deaths;
        mRecovered = recovered;
        mActive = active;
        mCritical = critical;
        mPop = pop;
        mFlagurl = flagurl;
        mtodayCases = todaycases;
        mtodayDeaths = todaydeaths;
        mtodayRecovered = todayrecovered;
        mflag = flag;

    }

    //following are the get methods for all the attributes of the country
    public String getmCountryName(){return mCountryName;}

    public long getmTimeInMilliseconds(){return mTimeInMilliseconds;}

    public String getmTotalCases(){return myFormat.format(mTotalCases);}

    public String getmDeaths(){return myFormat.format(mDeaths);}

    public String getmRecovered(){return myFormat.format(mRecovered);}

    public String getmActive(){return myFormat.format(mActive);}

    public String getmCritical(){return myFormat.format(mCritical);}

    public String getmPop(){return  myFormat.format(mPop);}

    public String getmFlagurl(){return mFlagurl;}

    public String getmtodayCases(){return myFormat.format(mtodayCases);}

    public String getmtodayDeaths(){return myFormat.format(mtodayDeaths);}

    public String getmtodayRecovered(){return myFormat.format(mtodayRecovered);}

    public Bitmap getMflag(){return mflag;}

}
