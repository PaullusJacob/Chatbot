// Jacob Paullus
// CS2336.502
// HW 5
// Description: Calls weather API and parses

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonPrimitive;
import org.json.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class humidityAPI
{

    static String startWebRequest(String city){
        String weatherURL = "http://api.wunderground.com/api/d1519e09a1608771/conditions/q/CA/"+ city +".json";
        StringBuilder result = new StringBuilder(); //will hold the java String after converting from JSON
        try {
            URL url = new URL(weatherURL); //the url we want to parse
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Used to make a single connection to that URL
            conn.setRequestMethod("GET"); //The Type of request we want to make
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); //pass in the instance of HttpURLConnection
            String line;
            while ((line = rd.readLine()) != null) { //turn BufferedReader value into type String
                result.append(line);
            }

            System.out.println(result);

            rd.close();
            //what do we want to search the API for?
            String windGust = parseJson(result.toString()); // calls parser
            String testString = (": The humidityAPI is currently: " + windGust );
            return testString;//result.toString();
        }
        catch(Exception e){return "Error! Exception: " + e;}
    }

    static String parseJson(String json) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();

        JsonObject currentObject = MasterWeatherObject.getAsJsonObject("current_observation"); // enters into current_o
        JsonPrimitive humidity = currentObject.getAsJsonPrimitive("relative_humidity"); // gets humidity


        return humidity.getAsString();
    }
}


