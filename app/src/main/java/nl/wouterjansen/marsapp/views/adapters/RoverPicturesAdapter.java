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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater roverInflater = context.getLayoutInflater();
        View roverPictures = roverInflater.inflate(R.layout.rover_row, parent, false);


        //Reference to data
        String singlePhoto = getItem(position);
        //Reference to view text
        TextView ID = (TextView) roverPictures.findViewById(R.id.pictureID);

        //Reference to image
        ImageView photo = (ImageView) roverPictures.findViewById(R.id.marsImage);

        ID.setText(singlePhoto);
        Log.d("IMAGE", img_srcArray[position]);

        Picasso.get().load(img_srcArray[position]).into(photo);
        //photo.setImageURI(Uri.parse("https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG"));

        //TODO: Fix onClick method
        /*
        View roverList = roverInflater.inflate(R.layout.activity_main, parent, false);
        roverList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("SelectedItem: ", i + "");

                String picturePosition = String.valueOf(adapterView.getItemAtPosition(i));
                Log.d("Image NO", picturePosition);

                Intent detailIntent = new Intent(context, RoverDetail.class);
                context.startActivity(detailIntent);
            }
        });
        */
        return roverPictures;
    }
}
