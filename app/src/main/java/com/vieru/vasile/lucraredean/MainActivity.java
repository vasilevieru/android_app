package com.vieru.vasile.lucraredean;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Kebab> kebabList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView cafe, restaurant,pizza, kebab,fastfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cafe = (TextView) findViewById(R.id.textViewCafe);
        restaurant = (TextView) findViewById(R.id.textViewRestaurant);
        pizza = (TextView) findViewById(R.id.textViewPizza);
        kebab = (TextView) findViewById(R.id.textViewKebab);
        fastfood = (TextView) findViewById(R.id.textViewFastFood);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.gridLayout), iconFont);
    }

    public void cafeOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("select","cafe");
        startActivity(intent);
    }

    public void restaurantOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("select","restaurant");
        startActivity(intent);
    }

    public void rateUsOnClick(View view) {
    }

    public void kebabOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("select","kebab");
        startActivity(intent);
    }

    public void fastFoodOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("select","fastfood");
        startActivity(intent);
    }

    public void pizzaOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("select","pizza");
        startActivity(intent);
    }
}
