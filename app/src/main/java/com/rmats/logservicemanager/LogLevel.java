package com.rmats.logservicemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class LogLevel implements Parcelable {
    private String level;

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    protected LogLevel() {
    }

    protected LogLevel(Parcel parcel) {
        level = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(level);
    }

    public static final Creator<LogLevel> CREATOR = new Creator<LogLevel>() {
        @Override
        public LogLevel createFromParcel(Parcel in) {
            return new LogLevel(in);
        }

        @Override
        public LogLevel[] newArray(int size) {
            return new LogLevel[size];
        }
    };
}
