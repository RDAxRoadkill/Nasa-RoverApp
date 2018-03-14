package nl.wouterjansen.marsapp.controllers;
import nl.wouterjansen.marsapp.models.AsyncResponse;
import nl.wouterjansen.marsapp.models.GetAPIData;

/**
 * Created by Wouter on 3/13/2018.
 */

public class APIController {
    //TODO: Cleanup data
    //APIController; Wrapper voor Async requests
    private final String API_KEY = "RcINb3QI7UFA8FGngjxYutYAsDnA7pifZWbhhnTh";

    public void getImages(AsyncResponse response) {
        GetAPIData apiData = new GetAPIData(response, API_KEY);
        apiData.execute();
    }
}