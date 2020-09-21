package com.example.helloworld;

import android.net.Uri;

public class Datas {
    private String Bound;
    private Integer RD;
    private String ImageID;

    public  Datas(){

    }

    public Integer getRD() {
        return RD;
    }

    public void setRD(Integer RD) {
        this.RD = RD;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        this.ImageID =imageID;
    }

    public String getBound() {
        return Bound;
    }

    public void setBound(String bound) {
        Bound = bound;
    }
}
