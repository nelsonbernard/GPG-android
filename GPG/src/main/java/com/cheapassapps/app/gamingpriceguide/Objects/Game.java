package com.cheapassapps.app.gamingpriceguide.Objects;

import java.net.URL;

/**
 * Created by Nelson on 8/23/2017.
 */

public class Game{

    private String imageName = "";
    private String consoleID = "";
    private String name = "";
    private String giantBombID = "";
    private int id = 0;
    private String loosePrice = "";
    private String completePrice = "";
    private String newPrice = "";
    private String gradedPrice = "";
    private String gameURL = "";
    private String description = "";
    private String deck = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getScreen_url() {
        return screen_url;
    }

    public void setScreen_url(String screen_url) {
        this.screen_url = screen_url;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private String screen_url = "";
    private String releaseDate = "";


    public Game(){

    }

    public String getConsoleID() { return consoleID; }

    public void setConsoleID(String consoleID) { this.consoleID = consoleID; }

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

    public String getLoosePrice() {
        return loosePrice;
    }

    public void setLoosePrice(String loosePrice) {
        this.loosePrice = loosePrice;
    }

    public String getCompletePrice() {
        return completePrice;
    }

    public void setCompletePrice(String completePrice) {
        this.completePrice = completePrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getGiantBombID() {
        return giantBombID;
    }

    public void setGiantBombID(String giantBombID) {
        this.giantBombID = giantBombID;
    }

    public String getGradedPrice() {
        return gradedPrice;
    }

    public void setGradedPrice(String gradedPrice) {
        this.gradedPrice = gradedPrice;
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }
}
