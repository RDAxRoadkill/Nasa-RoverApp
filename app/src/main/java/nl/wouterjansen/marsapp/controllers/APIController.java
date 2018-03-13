package nl.wouterjansen.marsapp.controllers;
import nl.wouterjansen.marsapp.MainActivity;
import nl.wouterjansen.marsapp.models.HttpHandler;
import nl.wouterjansen.marsapp.models.RoverPictures;

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

import static android.content.ContentValues.TAG;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wouter on 3/13/2018.
 */

public class APIController {
    //APIController. Wrapper voor Async requests
    //api_key=RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh
    //fullname, img_src & id are needed
    private ArrayList<RoverPictures> roverPictures;
    private String mFileContents;
    ArrayList<HashMap<String, String>> roverDataList;

    public void getImages() {
        //Send API Key
        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-03-12&page=1&api_key=DEMO_KEY");
    }

    private class DownloadData extends AsyncTask<String, Void, String> {
        //First string: download location, void is usually used for a progress bar
        //Last string is for the result
        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            int responsCode = -1;
            String nasaURL = params[0];
            String response = "";

            Log.i(TAG, "doInBackground - " + nasaURL);
            try {
                //URL Object
                URL url = new URL(nasaURL);
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
                    Log.i(TAG, inputStream.toString());
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

            // Check of er een response is
            if(response == null || response == "") {
                Log.e(TAG, "onPostExecute kreeg een lege response!");
                return;
            }

            // Het resultaat is in ons geval een stuk tekst in JSON formaat.
            // Daar moeten we de info die we willen tonen uit filteren (parsen).
            // Dat kan met een JSONObject.
            JSONObject jsonObject;
            try {
                // Top level json object
                jsonObject = new JSONObject(response);

                // Get all users and start looping
                JSONArray users = jsonObject.getJSONArray("photos");
                for(int idx = 0; idx < users.length(); idx++) {
                    // array level objects and get user
                    JSONObject user = users.getJSONObject(idx);

                    // Get id, img_src
                    JSONObject name = user.getJSONObject("photos");
                    String id = name.getString("id");
                    String img_src = name.getString("img_src");

                    Log.i(TAG, "Got user " + id + " " + img_src);

                    // Get image url
                    //JSONObject picture = user.getJSONObject("picture");
                    //String imageurl = picture.getString("large");
                    //Log.i(TAG, imageurl);

                    // Create new Person object
                    HashMap<String, String> roverPhoto = new HashMap<>();

                    // adding each child node to HashMap key => value
                    roverPhoto.put("id", id);
                    //roverPhoto.put("full_name", full_name);
                    roverPhoto.put("img_src", img_src);
                    Log.e("DATA", roverPhoto.toString());
                    // adding roverPhoto to rover list
                    roverDataList.add(roverPhoto);

                }
            } catch( JSONException ex) {
                Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
            }
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
}
