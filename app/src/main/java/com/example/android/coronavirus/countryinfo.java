package com.example.android.coronavirus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class countryinfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countryinfo);
        int x = getIntent().getExtras().getInt("country_pos");
        CovidAdapter adapter = MainActivity.getAdapter();
        countryObject currentCountry = adapter.getItem(x);

        TextView name = (TextView) findViewById(R.id.country);
        name.setText(currentCountry.getmCountryName());

        // show The Image in a ImageView
        ImageView flag = (ImageView) findViewById(R.id.flag);
        flag.setImageBitmap(currentCountry.getMflag());

        TextView totaltitle = (TextView) findViewById(R.id.total_case_title);
        totaltitle.setText(R.string.total);

        TextView activetitle = (TextView) findViewById(R.id.active_case_title);
        activetitle.setText(R.string.active);

        TextView recoveredtitle = (TextView) findViewById(R.id.recovered_case_title);
        recoveredtitle.setText(R.string.recovered);

        TextView criticaltitle = (TextView) findViewById(R.id.critical_case_title);
        criticaltitle.setText(R.string.critical);

        TextView deathtitle = (TextView) findViewById(R.id.deaths_case_title);
        deathtitle.setText(R.string.deaths);

        TextView poptitle = (TextView) findViewById(R.id.pop_title);
        poptitle.setText(R.string.pop);

        TextView newrecovered = (TextView) findViewById(R.id.new_recovered_case_title);
        newrecovered.setText(R.string.newrecovered);

        TextView newdeaths = (TextView) findViewById(R.id.new_deaths_case_title);
        newdeaths.setText(R.string.newdeaths);

        TextView active = (TextView) findViewById(R.id.active_case);
        active.setText(currentCountry.getmActive());

        TextView recovered = (TextView) findViewById(R.id.recovered_case);
        recovered.setText(currentCountry.getmRecovered());

        TextView critical = (TextView) findViewById(R.id.critical_case);
        critical.setText(currentCountry.getmCritical());

        TextView deaths = (TextView) findViewById(R.id.deaths_case);
        deaths.setText(currentCountry.getmDeaths());

        TextView pop = (TextView) findViewById(R.id.pop);
        pop.setText(currentCountry.getmPop());

        TextView total = (TextView) findViewById(R.id.total_case);
        total.setText(currentCountry.getmTotalCases());

        TextView todaycases = (TextView) findViewById(R.id.todaycasestitle);
        String temp = "+ " + currentCountry.getmtodayCases() + " New Cases";
        todaycases.setText(temp);

        TextView todaydeaths = (TextView) findViewById(R.id.new_deaths_case);
        todaydeaths.setText(currentCountry.getmtodayDeaths());

        TextView todayrecovered = (TextView) findViewById(R.id.new_recovered_case);
        todayrecovered.setText(currentCountry.getmtodayRecovered());
    }
}
