package com.example.cities;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.internal.LinkedTreeMap;

public class Coord implements Parcelable {
    public double lat;
    public double lon;

    public Coord(LinkedTreeMap<String, Object> map) {
        lat = (double) map.get("lat");
        lon = (double) map.get("lon");
    }

    protected Coord(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };

    public Location toLocation() {
        Location result = new Location("");
        result.setLatitude(lat);
        result.setLongitude(lon);

        return result;
    }
}
