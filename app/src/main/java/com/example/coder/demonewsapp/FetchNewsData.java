package com.example.coder.demonewsapp;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class FetchNewsData extends AsyncTask<Void, Void, String> {

    LinkedList<String> mTitleList;
    LinkedList<String> mDescriptionList;

    FetchNewsData(LinkedList<String> titleList, LinkedList<String> descriptionList){
        this.mTitleList = titleList;
        this.mDescriptionList = descriptionList;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return NetworkUtils.getNewsData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

//        mTitleList = new LinkedList<>();
//        mDescriptionList = new LinkedList<>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("articles");

            int i = 0;

            while (i < itemsArray.length()){

                JSONObject news = itemsArray.getJSONObject(i);

                try {
                    mTitleList.add(news.getString("title"));
                    mDescriptionList.add(news.getString("description"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
                i++;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
