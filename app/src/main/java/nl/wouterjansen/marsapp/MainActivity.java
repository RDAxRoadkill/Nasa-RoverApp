package nl.wouterjansen.marsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nl.wouterjansen.marsapp.controllers.APIController;
import nl.wouterjansen.marsapp.models.AsyncResponse;
import nl.wouterjansen.marsapp.models.RoverPictures;
import nl.wouterjansen.marsapp.views.RoverDetail;
import nl.wouterjansen.marsapp.views.adapters.RoverPicturesAdapter;

public class MainActivity extends AppCompatActivity{
    ListView listView;
    //ListAdapter adapter;
    //ArrayList roverArray = new ArrayList();
    private APIController apiController = new APIController();
    private MainActivity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiController.getImages(new AsyncResponse() {
            @Override
            public void processFinish(RoverPictures[] output) {
                Log.d("WOOOW", output.toString());
                //String[] testArray = {output};

                //TODO: Move this to a seperate function
                String[] ids = new String[output.length];
                String[] img_srcs = new String[output.length];
                String[] camera_full_names = new String[output.length];

                for(int i = 0; i < output.length; i++) {
                    ids[i] = "Image ID: " + output[i].getId();
                    img_srcs[i] = output[i].getImg_src();
                    camera_full_names[i] = output[i].getFull_name();
                }

                RoverPicturesAdapter adapter = new RoverPicturesAdapter(context, ids, img_srcs, camera_full_names);
                listView = (ListView) findViewById(R.id.pictureList);
                listView.setAdapter(adapter);
            }
        });
    }
}
