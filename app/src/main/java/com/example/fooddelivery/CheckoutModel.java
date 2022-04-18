package com.example.fooddelivery;

public class CheckoutModel {

    private  int id;
    private String name, code, phone, province, city, zone, address;
    private long started, finished;


    public  CheckoutModel(){


    }

    public CheckoutModel(int id, String name, String code, String phone, String province, String city, String zone, String address,  long started, long finished) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.address = address;
        this.started = started;
        this.finished = finished;
    }

    public CheckoutModel(String name, String code, String phone, String province, String city, String zone, String address,  long started, long finished) {
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.address = address;
        this.started = started;
        this.finished = finished;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }

}
