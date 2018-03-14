package nl.wouterjansen.marsapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nl.wouterjansen.marsapp.R;
import nl.wouterjansen.marsapp.controllers.APIController;

/**
 * Created by Wouter on 3/14/2018.
 */

/**
 * By extending activity instead of AppCompatActivty
 * We get rid of the 'action bar'.
 * Easiest solution, since we do not need any ACP functions.
 */

public class RoverDetail extends Activity {

    private APIController API = new APIController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rover_detail);
        Intent intent = getIntent();

        //Try/Catch since the intentExtras could be null
        try {
            int id = (int) intent.getExtras().get("ID");
            String cName = (String) intent.getExtras().get("cName");
            String photo = (String) intent.getExtras().get("photo");
            View detailView = this.findViewById(android.R.id.content);

            //System.out.println(id);
            //System.out.println(cName);
            //System.out.println(photo);

            //Setting camera name
            TextView cameraName = (TextView) detailView.findViewById(R.id.roverDetail);
            cameraName.setText(cName);

            //Setting background
            final ImageView marsImage = (ImageView) detailView.findViewById(R.id.marsBackground);
            Picasso.get().load(photo).into(marsImage);

        } catch(NullPointerException e) {
            Log.d("RoverDetailNPE", e.getLocalizedMessage());
        }

    }
}