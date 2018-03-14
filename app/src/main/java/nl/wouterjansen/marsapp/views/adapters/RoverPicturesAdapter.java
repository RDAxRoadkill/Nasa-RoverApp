package nl.wouterjansen.marsapp.views.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import nl.wouterjansen.marsapp.MainActivity;
import nl.wouterjansen.marsapp.R;
import nl.wouterjansen.marsapp.views.RoverDetail;

/**
 * Created by Wouter on 3/13/2018.
 */

public class RoverPicturesAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] idArray;
    private String[] img_srcArray;
    private String[] full_nameArray;

    public RoverPicturesAdapter(Activity context, String[] idArray, String[] img_srcArray, String[] full_nameArray) {
        super(context, R.layout.rover_row, idArray);
        this.context = context;

        this.idArray = idArray;
        this.img_srcArray = img_srcArray;
        this.full_nameArray = full_nameArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mealInflater = LayoutInflater.from(getContext());
        View roverPictures = mealInflater.inflate(R.layout.rover_row, parent, false);

        //Reference to data
        String singlePhoto = getItem(position);
        //Reference to view text
        TextView ID = (TextView) roverPictures.findViewById(R.id.pictureID);

        //Reference to image
        ImageView photo = (ImageView) roverPictures.findViewById(R.id.marsImage);

        ID.setText(singlePhoto);
        Log.d("IMAGE", img_srcArray.toString());
        //photo.setImageURI(Uri.parse("https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"));

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
