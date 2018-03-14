package nl.wouterjansen.marsapp.models;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Wouter on 3/14/2018.
 */

public class GetAPIData extends AsyncTask<Void, Void, JSONArray> {
        //First string: download location, void is usually used for a progress bar
        //Last string is for the result
        public AsyncResponse res = null;

        private String API_KEY;

        public GetAPIData (AsyncResponse response, String API_KEY) {
            this.API_KEY = API_KEY;
            this.res = response;
        }

        //TODO: Clean up function
        @Override
        protected JSONArray doInBackground(Void... params) {
            JSONArray apiData = new JSONArray();
            try {
                //URL Object
                //URL url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-03-12&page=1&api_key=" +API_KEY);
                URL url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=1&api_key=" +API_KEY);

                //Open Connection
                URLConnection urlConnection = url.openConnection();

                //Initializing HTTP Connection
                HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;

                //Set connection options
                httpConnection.setAllowUserInteraction(false);
                httpConnection.setInstanceFollowRedirects(true);
                httpConnection.setRequestMethod("GET");
                httpConnection.setRequestProperty("Content-Type", "application/json");

                //Excecute HTTP Connection
                httpConnection.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String plainJson = readStream(in);
                JSONObject temp = new JSONObject(plainJson);
                apiData = new JSONArray(temp.get("photos").toString());

            } catch (MalformedURLException e) {
                Log.e(TAG, "doInBackground MalformedURLex " + e.getLocalizedMessage());
            } catch (JSONException e) {
                Log.e(TAG, "doInBackground JSONException " + e.getLocalizedMessage());
            } catch (IOException e) {
                Log.e(TAG, "doInBackground IOException" + e.getLocalizedMessage());
            }
            return apiData;
        }

        protected void onPostExecute(JSONArray apiDataArray) {
            Log.i(TAG, "onPostExecute " + apiDataArray);
            RoverPictures[] roverPictures = new RoverPictures[apiDataArray.length()];
            try {
                for(int i = 0; i < apiDataArray.length(); i++) {
                    JSONObject pictureData = (JSONObject) apiDataArray.get(i);
                    JSONObject camera = (JSONObject) pictureData.get("camera");
                    roverPictures[i] = (new RoverPictures(pictureData.getInt("id"), pictureData.getString("img_src"), camera.getString("full_name")));
                }
            } catch( JSONException ex) {
                Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
            }
            Log.d("WEW ", roverPictures.toString());
            //Returning the variable
            res.processFinish(roverPictures);
        }

    private String readStream(BufferedReader in) {
        String chunk;
        String result = "";

        try {
            while ((chunk = in.readLine()) != null) {
                result += chunk;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}