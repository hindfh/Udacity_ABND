package com.example.hind.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPlacesFragment extends Fragment {


    public FoodPlacesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_places, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.string.al_baik,R.string.baik_time,R.drawable.baik));
        places.add(new Place(R.string.shobak,R.string.shobak_time,R.drawable.shobak));
        places.add(new Place(R.string.al_tazij,R.string.tazij_time,R.drawable.tazij));
        places.add(new Place(R.string.lokmah,R.string.lokma_time,R.drawable.lokmah));
        places.add(new Place(R.string.root,R.string.root_time,R.drawable.root));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list.
        PlaceAdapter adapter = new PlaceAdapter(getActivity(),places,R.color.category_food_places);

        // Find the {@link ListView} object in the view hierarchy of the l{@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlaceAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        return rootView;
    }
}
