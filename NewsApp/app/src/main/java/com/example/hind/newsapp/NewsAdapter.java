package com.example.hind.newsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by hind on 27/05/2018 AD.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news){
        super(context,0,news);
    }

    /**
     * Returns a list item view that displays News
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the news at the given position in the list of earthquakes
        News currentNews = getItem(position);


        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current news in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID author
        TextView autherView = (TextView) listItemView.findViewById(R.id.section);
        // Display the author of the current news in that TextView
        autherView.setText(currentNews.getSection());

        // Find the TextView with view ID title
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // Display the title of the current news in that TextView
        authorView.setText(currentNews.getAuthor());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String datte = currentNews.getPublishedAt();
        datte = datte.substring(0,datte.indexOf("T"));

        // Display the date of the current news in that TextView
        dateView.setText(datte);


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}

