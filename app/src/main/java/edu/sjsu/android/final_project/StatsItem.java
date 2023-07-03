package edu.sjsu.android.final_project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StatsItem implements Parcelable {
    private String mood;
    private String date;
    private String month;
    private String year;

    private static String length="0";

    public StatsItem(String length){
        this.length = length;
    }

    public StatsItem(String date, String month, String year,String mood){
        this.date = date;
        this.month = month;
        this.year = year;
        this.mood = mood;
    }

    public String getName(){
        return mood;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public static String getLength() {
        return length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
