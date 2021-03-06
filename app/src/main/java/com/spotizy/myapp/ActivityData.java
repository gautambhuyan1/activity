package com.spotizy.myapp;

/**
 * Created by Gautam_Bhuyan on 1/20/2016.
 */
public class ActivityData {
    private String interestId;
    private String activityId;
    private String userid;
    private String username;
    private String date;
    private double latitude;
    private double longitude;
    private String name;
    private int likes;
    private int shares;
    private String place;
    private String time;

    public ActivityData (String interestId, String userid, String username, String activityId, double latitude, double longitude, String name, String place, String date, String time, int likes, int shares) {
        this.interestId = interestId;
        this.activityId = activityId;
        this.userid = userid;
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = new String(name);
        this.date = new String(date);
        this.likes = likes;
        this.shares = shares;
        this.place = place;
        this.time = time;
    }

    public String getInterestId() {
        return this.interestId;
    }

    public String getUserId() {
        return this.userid;
    }

    public String getUserName() {
        return this.username;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getActivityName() {
        return this.name;
    }

    public String getPlace() {return this.place;}

    public String getDate() {return this.date;}

    public String getTime() {return this.time;}

    public int getLikes() {return this.likes;}

    public int getShares() {return this.shares;}
}