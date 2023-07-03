package edu.sjsu.android.final_project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ListItem implements Parcelable {
    private String name;
    private int image;
    public ListItem(String name,int image){
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public int getImage(){
        return image;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
