package com.cheapassapps.app.gamingpriceguide.Objects;

import java.net.URL;

public class Console{

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsoleID() { return consoleID;}

    public void setConsoleID(String consoleID) {
        this.consoleID = consoleID;
    }

    private String consoleID = "";
    private String imageURL = "";
    private String name = "";
    private int id = 0;

    public Console(){

    }
}
