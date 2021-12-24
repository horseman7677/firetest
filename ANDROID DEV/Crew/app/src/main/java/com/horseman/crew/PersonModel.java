package com.horseman.crew;

public class PersonModel {
    private String image, name, agency, status, wikipedia;

    public PersonModel() {
    }


    public PersonModel(String image, String name, String agency, String status, String wikipedia) {
        this.image = image;
        this.name = name;
        this.agency = agency;
        this.status = status;
        this.wikipedia = wikipedia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }
}
