package com.example.internalship;

public class ViewPageItem {
    String idPac;
    String imageID;
    String heading, description;

    public ViewPageItem(String idPac,String imageID, String heading, String description) {
        this.imageID = imageID;
        this.heading = heading;
        this.description = description;
        this.idPac = idPac;
    }
}
