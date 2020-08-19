package com.example.learndemo;

public class Message {

    private String address;
    private String body;
    private String date;
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Message [address=" + address + ", body=" + body + ", date="
                + date + ", id=" + id + "]";
    }
}
