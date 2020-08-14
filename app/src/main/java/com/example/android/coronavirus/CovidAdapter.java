package com.example.android.coronavirus;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CovidAdapter extends ArrayAdapter<countryObject> {

    public CovidAdapter(Activity context, ArrayList<countryObject> country){
        super(context,0,country);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }
        countryObject currentCountry = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.country);
        name.setText(currentCountry.getmCountryName());
        Date dateObject = new Date(currentCountry.getmTimeInMilliseconds());
        TextView dateincontext = (TextView) listItemView.findViewById(R.id.last_update_time);
        dateincontext.setText(R.string.last_update);

        TextView at = (TextView) listItemView.findViewById(R.id.at);
        at.setText(R.string.at);

        // show The Image in a ImageView
        ImageView flag = (ImageView) listItemView.findViewById(R.id.flag);
        flag.setImageBitmap(currentCountry.getMflag());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current country in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current country in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
