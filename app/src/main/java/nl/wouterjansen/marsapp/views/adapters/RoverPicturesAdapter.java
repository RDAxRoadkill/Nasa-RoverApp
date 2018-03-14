package nl.wouterjansen.marsapp.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import nl.wouterjansen.marsapp.R;

/**
 * Created by Wouter on 3/13/2018.
 */

public class RoverPicturesAdapter extends ArrayAdapter<String>  {

    public RoverPicturesAdapter(Context context, String[] pictureArray){
        super(context, R.layout.rover_row, pictureArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mealInflater = LayoutInflater.from(getContext());
        View roverPictures = mealInflater.inflate(R.layout.rover_row, parent, false);

        //Reference to data
        String singlePhoto = getItem(position);
        //Reference to view text
        TextView photoID = (TextView) roverPictures.findViewById(R.id.pictureID);

        //Reference to image
        //ImageView mealImage = (ImageView) mealView.findViewById(R.id.mealImage);

        photoID.setText(singlePhoto);

        return roverPictures;
    }

}
