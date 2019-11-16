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
public class ActivityPlacesFragment extends Fragment {


    public ActivityPlacesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_places, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.string.kiswa_factory, R.string.kiswa_factory_desc));
        places.add(new Place(R.string.annabi_museum, R.string.annabi_museum_desc));
        places.add(new Place(R.string.hosainiah_park, R.string.hosainiah_park_desc));
        places.add(new Place(R.string.taif_Chairlifts, R.string.taif_Chairlifts_desc));
        places.add(new Place(R.string.aldorrah, R.string.aldorrah_desc));
        places.add(new Place(R.string.jeddah, R.string.jeddah_desc));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list.
        PlaceAdapter adapter = new PlaceAdapter(getActivity(),places,R.color.category_activities);

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
