package nl.wouterjansen.marsapp.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        roverPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SelectedItem: ", position + "");
                Log.d("Rover adapter", "is working");

                Intent detailIntent = new Intent(context, RoverDetail.class);
                int cleanPosition = Integer.valueOf(idArray[position].split(": ")[1]);

                detailIntent.putExtra("ID", cleanPosition);
                detailIntent.putExtra("photo", img_srcArray[position]);
                detailIntent.putExtra("cName", full_nameArray[position]);
                context.startActivity(detailIntent);
            }
        });

        return roverPictures;
    }
}
