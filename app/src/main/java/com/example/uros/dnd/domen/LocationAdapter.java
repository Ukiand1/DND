package com.example.uros.dnd.domen;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.uros.dnd.R;
import com.example.uros.dnd.activities.EditActionActivity;
import com.example.uros.dnd.db.LocationDataSource;

import java.util.List;

/**
 * Created by Nikola on 9/5/2015.
 */
public class LocationAdapter extends ArrayAdapter<Location> {

    List<Location> _Locations;
    Activity _activity;
    private LocationDataSource locationDatasource;

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
            final Location location = getItem(position);

        if (location != null) {
            TextView lName = (TextView) v.findViewById(R.id.locationName);
            final Switch lSwitch = (Switch) v.findViewById(R.id.locationSwitch);




            if (lName != null) {
                lName.setText(location.getName());
            }

            if(lSwitch != null){
                if(location.isEnabled())
                    lSwitch.setChecked(true);
            }

            lSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int status = 0;
                    if (lSwitch.isChecked())
                        status = 1;
                    locationDatasource = new LocationDataSource(getContext());
                    locationDatasource.updateLocationStatus(location.getLocation_id(),status);
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    Intent intent = new Intent(getContext(),EditActionActivity.class);
                    intent.putExtra("lName", location.getName());
                    intent.putExtra("lId",location.getLocation_id());
                    Action a = location.getAction();
                    intent.putExtra("actionId",a.getActionId());
                    intent.putExtra("actionName",a.getName());
                    intent.putExtra("actionCall",a.getCall());
                    intent.putExtra("actionVibrate",a.isVibrate());
                    intent.putExtra("actionSound",a.getSound());
                    //Dodati vrednost switcha boxa, ??????
                    getContext().startActivity(intent);
                }
            });




        }

        return v;
    }




}
