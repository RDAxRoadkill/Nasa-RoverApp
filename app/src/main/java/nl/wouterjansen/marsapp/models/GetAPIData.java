package nl.wouterjansen.marsapp.models;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class GetAPIData extends AsyncTask<String, Void, String> {
        //First string: download location, void is usually used for a progress bar
        //Last string is for the result
        public AsyncResponse res = null;
        private ArrayList<RoverPictures> roverPictures;
        private String mFileContents;
        private String API_KEY;

        ArrayList<HashMap<String, String>> roverDataList = new ArrayList<>();

        public GetAPIData (AsyncResponse response, String API_KEY) {
            this.API_KEY = API_KEY;
            this.res = response;
        }

        //TODO: Clean up function
        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            int responsCode = -1;
            String response = "";

            try {
                //URL Object
                URL url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-03-12&page=1&api_key=" +API_KEY);
                //Open Connection
                URLConnection urlConnection = url.openConnection();

                if(!(urlConnection instanceof HttpURLConnection)) {
                    return null;
                }

                //Initializing HTTP Connection
                HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
                httpConnection.setAllowUserInteraction(false);
                httpConnection.setInstanceFollowRedirects(true);
                httpConnection.setRequestMethod("GET");

                //Excecute HTTP Connection
                httpConnection.connect();

                //Check responsecode
                responsCode = httpConnection.getResponseCode();
                if(responsCode == httpConnection.HTTP_OK) {
                    inputStream = httpConnection.getInputStream();
                    response = getStringFromInputStream(inputStream);
                    Log.i(TAG, "doInBackground response = " + response);
                } else {
                    Log.e(TAG, "Invalid response");
                }
            } catch (MalformedURLException e) {
                Log.e(TAG, "doInBackground MalformedURLex " + e.getLocalizedMessage());
            } catch (IOException e) {
                Log.e(TAG, "doInBackground IOException" + e.getLocalizedMessage());
            }
            return response;
        }

        protected void onPostExecute(String response) {
            Log.i(TAG, "onPostExecute " + response);
            JSONArray imageArray = new JSONArray();
            // Check of er een response is
            if(response == null || response == "") {
                Log.e(TAG, "onPostExecute kreeg een lege response!");
                return;
            }

            JSONObject jsonObject;
            try {
                // Top level json object
                jsonObject = new JSONObject(response);

                // Get all users and start looping
                JSONArray photos = jsonObject.getJSONArray("photos");

                Log.i("WHOO", photos.toString());

                for (int i = 0; i < photos.length(); i++) {
                    JSONObject jsonobject = photos.getJSONObject(i);
                    String id = jsonobject.getString("id");
                    String img_src = jsonobject.getString("img_src");

                    //Get Full_name (within another object)
                    JSONObject camera = jsonobject.getJSONObject("camera");
                    String full_name = camera.getString("full_name");

                    //imageArray = id
                }
               /*
                for(int i = 0; i < photos.length(); i++) {
                    //TODO: Use a proper data set instead of hashmaps
                    // array level objects and get user
                    JSONObject user = photos.getJSONObject(i);

                    String id = user.getString("id");
                    String img_src = user.getString("img_src");

                    // tmp hash map for single contact
                    HashMap<String, String> roverPhoto = new HashMap<>();

                    //Get ID & Image
                    roverPhoto.put("id", id);
                    roverPhoto.put("img_src", img_src);

                    //Get Full_name (within another object)
                    JSONObject picture = user.getJSONObject("camera");
                    String full_name = picture.getString("full_name");

                    roverPhoto.put("full_name", full_name);

                    // adding roverPhoto to rover list
                    roverDataList.add(roverPhoto);
                }
                 */
            } catch( JSONException ex) {
                Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
            }
            Log.d("WEW ", roverDataList.toString());

            //Returning the variable
            //res.processFinish(roverDataList.toString());
        }

        private  String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();
        }
}