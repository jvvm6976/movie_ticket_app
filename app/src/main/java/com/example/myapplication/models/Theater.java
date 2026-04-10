package com.example.myapplication.models;

import java.io.Serializable;

public class Theater implements Serializable {
    private String theaterId;
    private String name;
    private String address;
    private String city;

    public Theater() {}

    public String getTheaterId() { return theaterId; }
    public void setTheaterId(String theaterId) { this.theaterId = theaterId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}