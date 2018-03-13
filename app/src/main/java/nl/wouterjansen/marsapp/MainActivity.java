package nl.wouterjansen.marsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nl.wouterjansen.marsapp.controllers.APIController;
import nl.wouterjansen.marsapp.models.RoverPictures;
import nl.wouterjansen.marsapp.views.adapters.RoverPicturesAdapter;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ListAdapter adapter;
    ArrayList roverArray = new ArrayList();
    APIController apiController = new APIController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiController.getImages();

        //TODO: Use actual data and fix this structure up.
        String[] roverArray = {"Spagett", "Twinkies", "Potato"};
        final RoverPictures roverPictures = new RoverPictures();
        roverPictures.id = 666;
        adapter = new RoverPicturesAdapter(this, roverArray);
        listView = (ListView) findViewById(R.id.pictureList);
        listView.setAdapter(adapter);
    }

}
