package com.vieru.vasile.lucraredean;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static List<Kebab> kebabList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Toolbar toolbar;
    private static String select = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setBackgroundColor(255);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back32));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kebabList.clear();
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(kebabList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        select = intent.getStringExtra("select");
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                  //      Kebab kebab = kebabList.get(position);
                        Intent intent = new Intent(ListActivity.this, ItemActivity.class);
                       //intent.putExtra("photo", kebab.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
        getKebabs();
        adapter.notifyDataSetChanged();

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                kebabList.clear();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static List<Kebab> getKebabs() {
        HttpClient httpClient = new DefaultHttpClient();
        String url = null;

        if(select.compareTo("cafe") == 0) {
             url = "http://192.168.0.16:8080/cafe";
        }else if(select.compareTo("restaurant")==0){
            url = "http://192.168.0.16:8080/restaurant";
        }else if(select.compareTo("kebab")==0){
            url = "http://192.168.0.16:8080/kebab";
        }else if(select.compareTo("fastfood")==0){
            url = "http://192.168.0.16:8080/fastfood";
        }else if(select.compareTo("pizza")==0){
            url = "http://192.168.0.16:8080/pizza";
        }

        HttpGet getMethod = new HttpGet(url);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpContext localContext = new BasicHttpContext();
        try {
            HttpResponse response = httpClient.execute(getMethod, localContext);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                String result = EntityUtils.toString(response.getEntity());

                JSONArray ja = new JSONArray(result);

                int n = ja.length();
                for (int i = 0; i < n; i++) {

                    JSONObject jo = ja.getJSONObject(i);

                    int id = jo.getInt("id");
                    String name = jo.getString("name");
                    String addresse = jo.getString("addresse");

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
