package com.vieru.vasile.lucraredean;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CustomVolleyRequest extends AsyncTask<String, Void, String>{

    protected String doInBackground(String... urls) {

        return readJSONFeed(urls[0]);
    }

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();

            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    protected void onPostExecute(String result) {

        JSONObject json;
        try {
            json = new JSONObject(result);

            ////CREATE A JSON OBJECT////

            JSONObject data = json.getJSONObject("product");

            ////GET A STRING////

            String title = data.getString("name");

            //Similarly you can get other types of data
            //Replace String to the desired data type like int or boolean etc.

        } catch (JSONException e1) {
            e1.printStackTrace();

        }

        //GETTINGS DATA FROM JSON ARRAY//

        try {

            JSONObject jsonObject = new JSONObject(result);
            JSONArray products = new JSONArray(
                    jsonObject.getString("name"));

            JSONObject productItems = products
                    .getJSONObject(1);

        } catch (Exception e) {
            Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
        }
    }
}