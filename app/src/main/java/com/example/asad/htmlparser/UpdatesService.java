package com.example.asad.htmlparser;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.asad.htmlparser.Database.DatabaseHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Asad on 8/11/2015.
 */
public class UpdatesService extends IntentService {

    public UpdatesService() {
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("Here", "Inservice");

        DatabaseHandler handler = new DatabaseHandler(getApplicationContext(), null, null, 1);

        int lastRID = Integer.parseInt(handler.getLastRID());
        Log.d("onHandleIntent", "Last RID in Service " + lastRID);

        int ridToSearch = lastRID + 1;
        String data = new String();

        String URL = "http://results.hamariweb.com/showresult.aspx?rno=300556&rid=" + ridToSearch;

        Document doc = null;
        try {
            doc = Jsoup.connect(URL).timeout(30*1000).get();

            Elements resultHeader = doc.getElementsByClass("form-horizontal");

            Elements h4Element = resultHeader.select("h4");

            if (!(h4Element.size() > 1)){
                if (!h4Element.isEmpty()){

                    String tempData = h4Element.text();

                    if (!tempData.equals("Results") || !tempData.equals("Result") || !tempData.equals("results")){
                        data = tempData.replace("Results of ", "");

                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction("com.example.asad.htmlparser");
                        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        broadcastIntent.setClass(getApplicationContext(), MainActivity.UpdatesReceiver.class);

                        broadcastIntent.putExtra("TEXT", data);
                        broadcastIntent.putExtra("RID", String.valueOf(ridToSearch));

                        sendBroadcast(broadcastIntent);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

