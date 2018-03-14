package nl.wouterjansen.marsapp.views.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import nl.wouterjansen.marsapp.R;

/**
 * Created by Wouter on 3/13/2018.
 */

public class RoverPicturesAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] ids;
    private String[] img_srcs;
    private String[] camera_full_names;

    public RoverPicturesAdapter(Activity context, String[] ids, String[] img_srcs, String[] camera_full_names) {
        super(context, R.layout.rover_row, ids);
        this.context = context;

        this.ids = ids;
        this.img_srcs = img_srcs;
        this.camera_full_names = camera_full_names;
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

/* Old
    public RoverPicturesAdapter(Context context, String[] pictureArray) {
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
   */
}
