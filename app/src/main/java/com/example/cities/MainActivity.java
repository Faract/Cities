package com.example.cities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText distanceEdit;
    ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        distanceEdit = findViewById(R.id.editTextNumber);

        try {
            Gson gson = new Gson();
            InputStream inputStream = getAssets().open("city.list.min.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            ArrayList<LinkedTreeMap<String, Object>> list = gson.fromJson(inputStreamReader, ArrayList.class);

            cities = new ArrayList<>();
            for (LinkedTreeMap<String, Object> map : list) {
                cities.add(new City(map));
            }

            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void find(View view) {
        City city = (City) spinner.getSelectedItem();
        double distance = 0;
        try {
            distance = Double.parseDouble(distanceEdit.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "Неверно введено число", Toast.LENGTH_SHORT).show();
        }

        ArrayList<City> arr = new ArrayList<>();

        for (City c : cities) {
            if (c.id == city.id) continue;

            Location l1 = c.coord.toLocation();
            Location l2 = city.coord.toLocation();

            if (l1.distanceTo(l2) / 1000 <= distance) arr.add(c);
        }

        Intent i = new Intent(this, CitiesActivity.class);
        i.putParcelableArrayListExtra("a", arr);
        startActivity(i);
    }


}

