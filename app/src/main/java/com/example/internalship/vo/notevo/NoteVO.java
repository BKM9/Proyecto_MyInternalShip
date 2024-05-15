package com.example.internalship.vo.notevo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NoteVO implements Parcelable {
    private int id;
    private String asunto;
    private String cuerpo;

    protected NoteVO(Parcel in) {
        id = in.readInt();
        asunto = in.readString();
        cuerpo = in.readString();
    }

    public static final Creator<NoteVO> CREATOR = new Creator<NoteVO>() {
        @Override
        public NoteVO createFromParcel(Parcel in) {
            return new NoteVO(in);
        }

        @Override
        public NoteVO[] newArray(int size) {
            return new NoteVO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public NoteVO(int id, String asunto, String cuerpo) {
        this.id = id;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public NoteVO() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(asunto);
        dest.writeString(cuerpo);
    }
}
