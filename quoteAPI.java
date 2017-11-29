// Jacob Paullus
// CS2336.502
// HW 5
// Description: Calls quote API and parses

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class quoteAPI
{

    static String startWebRequest(){
        String weatherURL = "http://quotesondesign.com/api/3.0/api-3.0.json";
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
            String quoteString = parseJson(result.toString()); // calls parser
            String testString = (quoteString);
            return testString;//result.toString();
        }
        catch(Exception e){return "Error! Exception: " + e;}
    }

    static String parseJson(String json) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterObject = jelement.getAsJsonObject();

        JsonPrimitive quoteObject = MasterObject.getAsJsonPrimitive("quote"); // grabs quote item
        JsonPrimitive authorObject = MasterObject.getAsJsonPrimitive("author"); // grabs author item

        // turn into strings
        String one = quoteObject.getAsString();
        String two = authorObject.getAsString();

        String finalString = (": \"" + one + "\" -" + two); // combine


        return finalString;
    }
}


