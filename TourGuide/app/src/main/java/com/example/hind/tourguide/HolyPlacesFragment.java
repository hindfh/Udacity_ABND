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
public class HolyPlacesFragment extends Fragment {


    public HolyPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_places, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(R.string.holy_mousqe,R.string.holy_mousqe_desc,R.drawable.haram));
        places.add(new Place(R.string.arafa,R.string.arafa_desc,R.drawable.rahma));
        places.add(new Place(R.string.mina,R.string.mina_desc,R.drawable.mina));
        places.add(new Place(R.string.muzdalifah,R.string.muzdalifah_desc,R.drawable.muzdalifah));
        places.add(new Place(R.string.noor_mountain,R.string.noor_desc,R.drawable.noor));

        // Create an {@link PlaceAdapter}, whose data source is a list of {@link Place}s. The
        // adapter knows how to create list items for each item in the list.
        PlaceAdapter adapter = new PlaceAdapter(getActivity(),places,R.color.category_Holy_places);

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
