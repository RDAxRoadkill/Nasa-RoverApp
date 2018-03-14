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
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

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

    //TODO: Cleanup data
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater roverInflater = context.getLayoutInflater();
        View roverPictures = roverInflater.inflate(R.layout.rover_row, parent, false);


        //Reference to data
        String singlePhoto = getItem(position);
        //Reference to view text
        TextView ID = (TextView) roverPictures.findViewById(R.id.pictureID);

        //Reference to image
        final ImageView photo = (ImageView) roverPictures.findViewById(R.id.marsImage);

        ID.setText(singlePhoto);
        //Log.d("IMAGE", img_srcArray[position]);

        Picasso.get().load(img_srcArray[position]).into(photo);
        //photo.setImageURI(Uri.parse("https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"));

        //TODO: Fix onClick method

        roverPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SelectedItem: ", position + "");
                Log.d("Rover adapter", "is working");

                Intent detailIntent = new Intent(context, RoverDetail.class);
                int cleanPosition = Integer.valueOf(idArray[position].split(": ")[1]);

                //System.out.println(cleanPosition);
                //System.out.println(img_srcArray[position]);
                //System.out.println(full_nameArray[position]);

                detailIntent.putExtra("ID", cleanPosition);
                detailIntent.putExtra("photo", img_srcArray[position]);
                detailIntent.putExtra("cName", full_nameArray[position]);
                context.startActivity(detailIntent);
            }
        });

        return roverPictures;
    }
}
