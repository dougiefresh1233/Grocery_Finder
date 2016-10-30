package com.example.dougl.grocery_finder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import static com.example.dougl.grocery_finder.Test_API_Activity.API_INPUT;
import static com.example.dougl.grocery_finder.Test_API_Activity.API_KEY;
import static com.example.dougl.grocery_finder.Test_API_Activity.API_NAME;
import static com.example.dougl.grocery_finder.Test_API_Activity.API_URL;
import static com.example.dougl.grocery_finder.Test_API_Activity.inputText;
import static com.example.dougl.grocery_finder.Test_API_Activity.responseView;

public class Test_API_Activity extends AppCompatActivity {

    /**
     * Variables needed to run the API
     */
    static final String API_KEY = "?APIKEY=b6984f5f77";
    static final String API_URL = "http://www.SupermarketAPI.com/api.asmx/";
    static final String API_NAME = "GetGroceries";
    static final String API_INPUT = "&SearchText=";

    String input = null;

    private XML_Handler obj;
    Vector<String> results;

    static TextView responseView;
    static EditText inputText;
    Button runButton;

    public Test_API_Activity() throws XmlPullParserException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);
        runButton = (Button) findViewById(R.id.runButton);
        responseView = (TextView) findViewById(R.id.responseView);
        inputText = (EditText) findViewById(R.id.inputText);
        results = new Vector<String>();

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = inputText.getText().toString();
                responseView.setText("");
                obj = new XML_Handler(API_URL + API_NAME + API_KEY + API_INPUT + input);
                obj.fetchXML();
                while(obj.parsingComplete);
                results = obj.getStringList();
                for(int i=0; i<results.size(); i++) {
                    responseView.append(results.get(i));
                }
            }
        });

    }
}
