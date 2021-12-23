package com.example.cities;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.internal.LinkedTreeMap;

public class City implements Parcelable {
    public int id;
    public String name;
    public String country;
    public Coord coord;

    public City(LinkedTreeMap<String, Object> map) {
        id = ((Double) map.get("id")).intValue();
        name = (String) map.get("name");
        country = (String) map.get("country");
        coord = new Coord((LinkedTreeMap<String, Object>) map.get("coord"));
    }

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        country = in.readString();
        coord = in.readParcelable(Coord.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeParcelable(coord, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}

