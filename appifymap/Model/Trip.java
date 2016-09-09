package com.djcanadastudio.appifymap.Model;

/**
 * Created by desenguo on 2016-09-08.
 */
public class Trip {
    private String pickup_address;
    private String pickup_city;
    private String pickup_country;
    private String pickup_time;
    private String pickup_time_zone;
    private double pickup_latitude;
    private double pickup_longitude;
    private int num_passengers;
    private String payment_method;
    private Phones phones;
    private String notes;
    private String account_number;
    private String passenger_name;

    public String getPickup_time_zone() {
        return pickup_time_zone;
    }

    public void setPickup_time_zone(String pickup_time_zone) {
        this.pickup_time_zone = pickup_time_zone;
    }


    public double getPickup_latitude() {
        return pickup_latitude;
    }

    public void setPickup_latitude(double pickup_latitude) {
        this.pickup_latitude = pickup_latitude;
    }


    public double getPickup_longitude() {
        return pickup_longitude;
    }

    public void setPickup_longitude(double pickup_longitude) {
        this.pickup_longitude = pickup_longitude;
    }

    public int getNum_passengers() {
        return num_passengers;
    }

    public void setNum_passengers(int num_passengers) {
        this.num_passengers = num_passengers;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getPickup_city() {
        return pickup_city;
    }

    public void setPickup_city(String pickup_city) {
        this.pickup_city = pickup_city;
    }


    public String getPassenger_name() {
        return passenger_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }


    public String getPickup_country() {
        return pickup_country;
    }

    public void setPickup_country(String pickup_country) {
        this.pickup_country = pickup_country;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }


    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

}
