package com.example.asad.htmlparser;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.asad.htmlparser.Database.DatabaseHandler;
import com.example.asad.htmlparser.Database.InfoHandler;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> ridsArray;
    ArrayList<String> ridsArrayValues;

    ArrayList<String> hscArray;
    ArrayList<Integer> hscRidsValues;

    ArrayList<String> sscArray;
    ArrayList<Integer> sscRidsValues;

    ArrayList<String> bcomArray;
    ArrayList<Integer> bcomRidsValues;

    ArrayList<String> baArray;
    ArrayList<Integer> baRidsValues;

    ArrayList<String> capArray;
    ArrayList<Integer> capRidsValues;

    ArrayList<String> diplomaArray;
    ArrayList<Integer> diplomaRidsValues;

    ArrayList<String> bscArray;
    ArrayList<Integer> bscRidsValues;

    ArrayList<String> miscArray;
    ArrayList<Integer> miscRidsValues;

    ArrayList<String> nullArray;
    ArrayList<Integer> nullRidsValues;

    ArrayList<String> recentArray;
    ArrayList<Integer> recentRidsValues;

    private DatabaseHandler databaseHandler;
    private TextView newsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this, null, null, 1);
        newsTextView = (TextView) findViewById(R.id.newsTextView);

        Spanned newsString = Html.fromHtml("<b>NEWS:</b>" + " Result of " + databaseHandler.getLastRIDText() + " has been announced!</p>");
        newsTextView.setText(newsString);

        ridsArray = new ArrayList<>();
        ridsArrayValues = new ArrayList<>();

        hscArray = new ArrayList<>();
        hscRidsValues = new ArrayList<>();

        sscArray = new ArrayList<>();
        sscRidsValues = new ArrayList<>();

        bcomArray = new ArrayList<>();
        bcomRidsValues = new ArrayList<>();

        baArray = new ArrayList<>();
        baRidsValues = new ArrayList<>();

        capArray = new ArrayList<>();
        capRidsValues = new ArrayList<>();

        diplomaArray = new ArrayList<>();
        diplomaRidsValues = new ArrayList<>();

        bscArray = new ArrayList<>();
        bscRidsValues = new ArrayList<>();

        miscArray = new ArrayList<>();
        miscRidsValues = new ArrayList<>();

        nullArray = new ArrayList<>();
        nullRidsValues = new ArrayList<>();

        recentArray = new ArrayList<>();
        recentRidsValues = new ArrayList<>();

        distributeData();

        Intent serviceIntent = new Intent(this, UpdatesService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, serviceIntent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pintent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Spanned newsString = Html.fromHtml("<b>NEWS:</b>" + " Result of " + databaseHandler.getLastRIDText() + " has been announced!</p>");
        newsTextView.setText(newsString);
        distributeData();
    }

    public void disclamerDialogView(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("DISCLAMER");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage("ResultsApp takes all the results from relevant board authorities or their websites and placed as it is based. We are not responsible for any error in this regard");
        builder.setIcon(R.drawable.ic_launcher);

        builder.create();
        builder.show();
    }

    public void aboutDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ABOUT");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage("Developed by: Asad Haider" + "\n" + "Build Version: 1.0" + "\n" + "Contact: asad.haider13@gmail.com");
        builder.setIcon(R.drawable.ic_launcher);

        builder.create();
        builder.show();

    }

    public static class UpdatesReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            String ridText = "";
            ridText = bundle.getString("TEXT");
            String ridValue = bundle.getString("RID");

            Log.d("On Receive", "Here in Static class: " + ridText + ", " + ridValue);

            DatabaseHandler databaseHandler = new DatabaseHandler(context, null, null, 1);
            InfoHandler infoHandler = new InfoHandler();
            infoHandler.setRid(ridValue);
            infoHandler.setRidText(ridText);
            databaseHandler.addRID(infoHandler);

            Intent resultIntent = new Intent(context, MainActivity.class);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Result Announcement")
                            .setContentText("Result of " + ridText + " has been announced!");

            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setAutoCancel(true);
            mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            mBuilder.setLights(Color.RED, 3000, 3000);

            int mNotificationId = 001;
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }
    }

    public void updateData(String text){
        newsTextView.setText("Result of " + text + " has been announced!");
        distributeData();
    }

    public void distributeData(){

        ridsArray = new ArrayList<>();
        ridsArrayValues = new ArrayList<>();

        hscArray = new ArrayList<>();
        hscRidsValues = new ArrayList<>();

        sscArray = new ArrayList<>();
        sscRidsValues = new ArrayList<>();

        bcomArray = new ArrayList<>();
        bcomRidsValues = new ArrayList<>();

        baArray = new ArrayList<>();
        baRidsValues = new ArrayList<>();

        capArray = new ArrayList<>();
        capRidsValues = new ArrayList<>();

        diplomaArray = new ArrayList<>();
        diplomaRidsValues = new ArrayList<>();

        bscArray = new ArrayList<>();
        bscRidsValues = new ArrayList<>();

        miscArray = new ArrayList<>();
        miscRidsValues = new ArrayList<>();

        nullArray = new ArrayList<>();
        nullRidsValues = new ArrayList<>();

        recentArray = new ArrayList<>();
        recentRidsValues = new ArrayList<>();

        ridsArray = databaseHandler.getCompleteData().get(1);
        ridsArrayValues = databaseHandler.getCompleteData().get(0);

        for (int i = 0; i < ridsArray.size(); i++) {

            String tempString = ridsArray.get(i).toUpperCase();
            int tempValue = Integer.parseInt(ridsArrayValues.get(i));

            if (tempString.contains("HSC") || tempString.contains("INTER")) {
                hscArray.add(tempString);
                hscRidsValues.add(tempValue);
            }
            else if (tempString.contains("SSC") || tempString.contains("MATRIC")) {
                sscArray.add(tempString);
                sscRidsValues.add(tempValue);
            }
            else if (tempString.contains("CAP") || tempString.contains("C.A.P")) {
                capArray.add(tempString);
                capRidsValues.add(tempValue);
            }
            else if (tempString.contains("MBBS") || tempString.contains("MBBS.")) {
                miscArray.add(tempString);
                miscRidsValues.add(tempValue);
            }
            else if (tempString.contains("BSC") || tempString.contains("B.SC")) {
                bscArray.add(tempString);
                bscRidsValues.add(tempValue);
            }
            else if (tempString.contains("B.A") || tempString.contains("BA")) {
                baArray.add(tempString);
                baRidsValues.add(tempValue);
            }
            else if (tempString.contains("DAE") || tempString.contains("DIPLOMA")) {
                diplomaArray.add(tempString);
                diplomaRidsValues.add(tempValue);
            }
            else if (tempString.contains("BCOM") || tempString.contains("B.COM")) {
                bcomArray.add(tempString);
                bcomRidsValues.add(tempValue);
            }
            else{
                miscArray.add(tempString);
                miscRidsValues.add(tempValue);
            }
        }

        for (int i = ridsArray.size() - 10; i < ridsArray.size(); i++){
            recentArray.add(ridsArray.get(i));
            recentRidsValues.add(Integer.parseInt(ridsArrayValues.get(i)));
        }
    }

    public void buttonOnClick(View view)
    {
        Intent ResultIntent = new Intent(MainActivity.this, RollNumber.class);

        switch(view.getId())
        {
            case R.id.recentResult:
                ResultIntent.putStringArrayListExtra("Results Text", recentArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", recentRidsValues);
                break;

            case R.id.matricResult:
                ResultIntent.putStringArrayListExtra("Results Text", sscArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", sscRidsValues);
                break;

            case R.id.interResult:
                ResultIntent.putStringArrayListExtra("Results Text", hscArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", hscRidsValues);
                break;

            case R.id.capformResult:
                ResultIntent.putStringArrayListExtra("Results Text", capArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", capRidsValues);
                break;
            case R.id.diplomaResult:
                ResultIntent.putStringArrayListExtra("Results Text", diplomaArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", diplomaRidsValues);
                break;

            case R.id.bscResult:
                ResultIntent.putStringArrayListExtra("Results Text", bscArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", bscRidsValues);
                break;

            case R.id.bcomResult:
                ResultIntent.putStringArrayListExtra("Results Text", bcomArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", bcomRidsValues);
                break;

            case R.id.baResult:
                ResultIntent.putStringArrayListExtra("Results Text", baArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", baRidsValues);
                break;

            case R.id.otherResult:
                ResultIntent.putStringArrayListExtra("Results Text", miscArray);
                ResultIntent.putIntegerArrayListExtra("Results Rids", miscRidsValues);
                break;
        }

        startActivity(ResultIntent);
    }
}

