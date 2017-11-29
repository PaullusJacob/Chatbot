// Jacob Paullus
// CS2336.502
// HW 5
// Description: handles reading/sending messages

import org.jibble.pircbot.*;

public class MyBot extends PircBot {

    windAPI weather = new windAPI();
    humidityAPI humid = new humidityAPI();
    tempAPI temp = new tempAPI();
    quoteAPI quote = new quoteAPI();

    public MyBot() {
        this.setName("MyBotPaullus");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        if (message.equalsIgnoreCase("time")) {
            String time = (new java.util.Date()).toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
        if (message.equalsIgnoreCase("wind")) {
            String string = weather.startWebRequest("75080");
            sendMessage(channel, sender + string + "");
        }
        if (message.equalsIgnoreCase("humidity")) {
            String string = humid.startWebRequest("75080");
            sendMessage(channel, sender + string + "");
        }
        if (message.equalsIgnoreCase("temperature")) {
            String string = temp.startWebRequest("75080");
            sendMessage(channel, sender + string + "");
        }
        if (message.equalsIgnoreCase("quote")) {
            String string = quoteAPI.startWebRequest();
            sendMessage(channel, sender + string + "");
        }
    }
}