package nl.wouterjansen.marsapp.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Wouter on 3/14/2018.
 */

public class VolleyRequest {
    String url = "";
    private final String API_KEY = "RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh";
    private RequestQueue requestQueue;
    private static Context context;


    public VolleyRequest(String URL) {
        this.url = URL;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("RESPONSE", response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error

                }
            });

            // Access the RequestQueue through your singleton class.
            //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
}
