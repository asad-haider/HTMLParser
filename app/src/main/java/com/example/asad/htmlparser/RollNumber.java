package com.example.asad.htmlparser;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class RollNumber extends ActionBarActivity {

    private int selectedRid = 9;
    private String rollNumber;
    private WebView webView;
    private RelativeLayout mainLayout;
    private EditText rollNoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.roll_no_layout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        webView = (WebView) findViewById(R.id.webView);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        rollNoEditText = (EditText) findViewById(R.id.rollnoEditText);

        Spinner textSpinner = (Spinner) findViewById(R.id.spinner);

        Intent getIntentFromMain = getIntent();

        ArrayList<String> textArray = getIntentFromMain.getStringArrayListExtra("Results Text");
        final ArrayList<Integer> textRids = getIntentFromMain.getIntegerArrayListExtra("Results Rids");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, textArray);

        textSpinner.setAdapter(spinnerAdapter);

        textSpinner.setSelected(true);

        textSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRid = textRids.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rollNoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        Button findButton = (Button) findViewById(R.id.findBtn);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()){
                    rollNumber = rollNoEditText.getText().toString();

                    if (rollNumber.isEmpty()){
                        Toast.makeText(RollNumber.this, "Please Type Your Roll Number!", Toast.LENGTH_SHORT).show();
                    }else{
                        new HTMLParser(RollNumber.this).execute(rollNumber, Integer.toString(selectedRid));
                    }

                }else{

                    Toast.makeText(RollNumber.this, "Internet not Working, Please Try Again", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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


    private class HTMLParser extends AsyncTask<String, Void, ArrayList<String>> {

        private ProgressDialog progressDialog;
        private Context context;
        private String type;

        public HTMLParser(Context context){
            this.context = context;
        }

        public void setType(String type) {
            this.type = type;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public void showProgressDialog(){
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Searching..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.create();
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            String URL = "http://results.hamariweb.com/showresult.aspx?rno=" + params[0] + "&rid=" + params[1];

            try {
                Document doc = Jsoup.connect(URL).get();

                Elements table = doc.select("table#ContentPlaceHolder1_dgResult");
                Elements message = doc.select("span#ContentPlaceHolder1_lblMsg");
                Elements failureResult = doc.select("span#ContentPlaceHolder1_lblResults");

                ArrayList<String> data = new ArrayList<>();

                if (!table.isEmpty())
                    data.add(table.toString());
                else if (!message.isEmpty())
                    data.add(message.toString());
                else if (!failureResult.isEmpty())
                    data.add(failureResult.toString());
                else
                    data.add("<p>Error 404, Result Not Found</p>");

                return data;

            } catch (IOException e) {
                e.printStackTrace();
                setType("Exception");
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);

            ArrayList<String> headerData = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (result != null) {

                WebView webView = (WebView) findViewById(R.id.webView);

                WebSettings settings = webView.getSettings();
                settings.setMinimumFontSize(20);
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                settings.setBuiltInZoomControls(true);
                settings.setDisplayZoomControls(true);

                Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                in.setDuration(1000);
                webView.startAnimation(in);
                webView.setVisibility(View.VISIBLE);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                webView.loadDataWithBaseURL(null, result.get(0), "text/html", "utf-8", null);
            }
        }
    }

}
