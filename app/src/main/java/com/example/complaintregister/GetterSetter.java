package com.example.complaintregister;

import android.os.Parcel;
import android.os.Parcelable;

public class GetterSetter implements Parcelable {
    String Department;
    String Category;
    String Description;
    String Email;


    public GetterSetter(String department, String category, String description,String email) {
        Department = department;
        Category = category;
        Email=email;
        Description = description;


    }
    protected GetterSetter(Parcel in){
        Department=in.readString();
        Category=in.readString();
        Description=in.readString();
        Email=in.readString();

    }

    public static final Creator<GetterSetter> CREATOR = new Creator<GetterSetter>() {
        @Override
        public GetterSetter createFromParcel(Parcel in) {
            return new GetterSetter(in);
        }

        @Override
        public GetterSetter[] newArray(int size) {
            return new GetterSetter[size];
        }
    };


    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email=email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Department);
        dest.writeString(Category);
        dest.writeString(Description);
        dest.writeString(Email);

    }
}
