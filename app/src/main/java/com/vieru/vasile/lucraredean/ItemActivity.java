package com.vieru.vasile.lucraredean;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ItemActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    List<Kebab> kebabList;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
      //Intent intent = getIntent();
      //String Id = intent.getStringExtra("photo");
      //id = Integer.parseInt(Id);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public  List<Kebab> getKebabs() {
        HttpClient httpClient = new DefaultHttpClient();

        //String url1 = "http://192.168.1.5:8080/"+ +"/"+id;
        String url = "http://192.168.1.6.8080/kebab";

        HttpGet getMethod = new HttpGet(url);
        HttpContext localContext = new BasicHttpContext();
        try {
            HttpResponse httpresponse = httpClient.execute(getMethod, localContext);
            StatusLine statusLine = httpresponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                String result = EntityUtils.toString(httpresponse.getEntity());

                JSONArray ja = new JSONArray(result);

                int n = ja.length();
                for (int i = 0; i < n; i++) {

                    JSONObject jo = ja.getJSONObject(i);

                    int id = jo.getInt("id");
                    String name = jo.getString("name");
                    String addresse = jo.getString("addresse");
                    String description = jo.getString("description");

                    byte[] byteArray = Base64.decode(jo.getString("photo"), Base64.DEFAULT);
                    Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                    float rating = (float) jo.getDouble("rate");

                    Kebab kebab = new Kebab(id,name, addresse, bmp1, rating);
                    kebabList.add(kebab);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kebabList;
    }
}
