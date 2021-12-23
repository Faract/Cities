package com.example.cities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        list = findViewById(R.id.cities);
        ArrayList<City> cities = (ArrayList<City>) getIntent().getExtras().get("a");
        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        list.setAdapter(adapter);
    }
}