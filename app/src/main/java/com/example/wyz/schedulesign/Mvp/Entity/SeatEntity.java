package com.example.wyz.schedulesign.Mvp.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WYZ on 2017/6/7.
 */

public class SeatEntity implements Parcelable {

    private String studio_id;
    private  int seat_row;
    private  int seat_column;
    /*
    * -1损坏 0未选 1已选
    * */
    private String seat_status;

    public SeatEntity(String studio_id, int seat_row, int seat_column, String seat_status) {
        this.studio_id = studio_id;
        this.seat_row = seat_row;
        this.seat_column = seat_column;
        this.seat_status = seat_status;
    }

    public String getStudio_id() {
        return studio_id;
    }

    public void setStudio_id(String studio_id) {
        this.studio_id = studio_id;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int seat_row) {
        this.seat_row = seat_row;
    }

    public int getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(int seat_column) {
        this.seat_column = seat_column;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studio_id);
        dest.writeInt(seat_row);
        dest.writeInt(seat_column);
        dest.writeString(seat_status);
    }
    public static final Parcelable.Creator<SeatEntity> CREATOR = new Parcelable.Creator<SeatEntity>()
    {
        public SeatEntity createFromParcel(Parcel in)
        {
            return new SeatEntity(in);
        }

        public SeatEntity[] newArray(int size)
        {
            return new SeatEntity[size];
        }
    };

    private SeatEntity(Parcel in)
    {
        studio_id = in.readString();
        seat_row=in.readInt();
        seat_column=in.readInt();
        seat_status=in.readString();
    }
}
