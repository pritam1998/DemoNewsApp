package com.example.coder.demonewsapp;

import android.net.Uri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    static final String BASE_URL = "https://newsapi.org/v2/top-headlines?";
    static final String SOURCES = "sources";
    static final String API_KEY = "apiKey";

    static String getNewsData(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String newsJSON = null;

        try {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(SOURCES,"techcrunch")
                    .appendQueryParameter(API_KEY, "364b86cca4cc4768a20215fc03f6a0e7")
                    .build();

            URL requestURL = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestURL
                    .openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            if(builder.length() == 0)
                return null;

            newsJSON = builder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return newsJSON;
    }
}
