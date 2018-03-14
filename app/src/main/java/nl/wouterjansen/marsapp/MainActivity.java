package nl.wouterjansen.marsapp;

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
import nl.wouterjansen.marsapp.models.GetAPIData;
import nl.wouterjansen.marsapp.models.RoverPictures;
import nl.wouterjansen.marsapp.models.VolleyRequest;
import nl.wouterjansen.marsapp.views.RoverDetail;
import nl.wouterjansen.marsapp.views.adapters.RoverPicturesAdapter;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    ListView listView;
    ListAdapter adapter;
    ArrayList roverArray = new ArrayList();
    private APIController apiController = new APIController();
    VolleyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = new VolleyRequest("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-03-12&page=1&api_key=");

        /*
        apiController.getImages(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Log.d("WOOOW", output);

            }
        });
        */


        //TODO: Use actual data and fix this structure up.
        String[] roverArray = {"Spagett", "Twinkies", "Potato"};
        final RoverPictures roverPictures = new RoverPictures();
        roverPictures.id = 666;
        adapter = new RoverPicturesAdapter(this, roverArray);
        listView = (ListView) findViewById(R.id.pictureList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("SelectedItem: ", i + "");

                        String picturePosition = String.valueOf(adapterView.getItemAtPosition(i));
                        Log.d("Image NO", picturePosition);

                        Intent detailIntent = new Intent(MainActivity.this, RoverDetail.class);
                        startActivity(detailIntent);
                    }
                }
        );
    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String output[]){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
    }




}
