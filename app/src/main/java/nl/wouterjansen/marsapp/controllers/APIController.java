package nl.wouterjansen.marsapp.controllers;
import nl.wouterjansen.marsapp.models.AsyncResponse;
import nl.wouterjansen.marsapp.models.GetAPIData;
import nl.wouterjansen.marsapp.models.RoverPictures;

/**
 * Created by Wouter on 3/13/2018.
 */

public class APIController {
    //APIController. Wrapper voor Async requests
    //api_key=RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh
    //fullname, img_src & id are needed
    private final String API_KEY = "RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh";

    public void getImages(AsyncResponse response) {
        GetAPIData apiData = new GetAPIData(response, API_KEY);
        apiData.execute();

        //Send API Key
        //DownloadData downloadData = new DownloadData();
        //downloadData.execute("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2018-03-12&page=1&api_key=RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh");
    }
}