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

    public String getIdPac() {
        return idPac;
    }

    public void setIdPac(String idPac) {
        this.idPac = idPac;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
