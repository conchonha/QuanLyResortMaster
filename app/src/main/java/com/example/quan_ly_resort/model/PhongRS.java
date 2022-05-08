package com.example.quan_ly_resort.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PhongRS implements Parcelable {
    private MutableLiveData<List<PhongRS>> listPhongRS = new MutableLiveData<>();

    public Boolean status;
    public String messsage;
    public String id;
    int cpd;
    public String state;
    public String type;

    public PhongRS() {
        //default
    }

    public PhongRS(Boolean status, String messsage, String id, int cpd, String state, String type) {
        this.status = status;
        this.messsage = messsage;
        this.id = id;
        this.cpd = cpd;
        this.state = state;
        this.type = type;
    }

    protected PhongRS(Parcel in) {
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        messsage = in.readString();
        id = in.readString();
        cpd = in.readInt();
        state = in.readString();
        type = in.readString();
    }

    public static final Creator<PhongRS> CREATOR = new Creator<PhongRS>() {
        @Override
        public PhongRS createFromParcel(Parcel in) {
            return new PhongRS(in);
        }

        @Override
        public PhongRS[] newArray(int size) {
            return new PhongRS[size];
        }
    };

    public void convertJson(JSONObject jsonObject) {
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<PhongRS> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                PhongRS phongRS = new PhongRS();
                phongRS.id = jsonArray.getJSONObject(i).getString("id");
                phongRS.cpd = jsonArray.getJSONObject(i).getInt("cpd");
                phongRS.state = jsonArray.getJSONObject(i).getString("state");
                phongRS.type = jsonArray.getJSONObject(i).getString("type");
                list.add(phongRS);
            }
            listPhongRS.postValue(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<PhongRS>> getListLive() {
        return listPhongRS;
    }

    @Override
    public String toString() {
        return "PhongRS{" +
                "listPhongRS=" + listPhongRS +
                ", status=" + status +
                ", messsage='" + messsage + '\'' +
                ", id='" + id + '\'' +
                ", cpd=" + cpd +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(true);
        parcel.writeString(messsage);
        parcel.writeString(id);
        parcel.writeInt(cpd);
        parcel.writeString(state);
        parcel.writeString(type);
    }
}
