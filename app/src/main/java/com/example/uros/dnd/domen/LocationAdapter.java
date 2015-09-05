package com.example.uros.dnd.domen;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.uros.dnd.R;

import java.util.List;

/**
 * Created by Nikola on 9/5/2015.
 */
public class LocationAdapter extends ArrayAdapter<Location> {

    List<Location> _Locations;
    Activity _activity;

    public LocationAdapter(Context context, int resource, List<Location> locations) {
        super(context, resource, locations);
        _Locations = locations;
    }

    @Override
    public int getCount() {
         return _Locations.size();
    }

    @Override
    public Location getItem(int i) {
        return _Locations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.location_adapter, null);
        }

       //ClipData.Item p = getItem(position);
            Location location = getItem(position);

        if (location != null) {
            TextView lName = (TextView) v.findViewById(R.id.locationName);
            Switch lSwitch = (Switch) v.findViewById(R.id.locationSwitch);


            if (lName != null) {
                lName.setText(location.getName());
            }

            if(lSwitch != null){
                if(location.isEnabled())
                    lSwitch.setChecked(true);
            }


        }

        return v;
    }


}
