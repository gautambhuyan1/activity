package com.spotizy.myapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gautam_Bhuyan on 1/16/2016.
 */
public class WebApiDataTask extends AsyncTask<String, Integer, String> {

    private Activity activity;
    private Context context;
    public WebApiDataTask(Activity act ) {
        super();
        this.activity = act;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... str) {
        String strURL = "https://gautambhuyan.herokuapp.com";
        //String strURL = "http://10.0.0.4:5000";
        //String strURL = "http://172.20.20.20:5000";
        String method = str[0];
        String methodName = str[1];
        String params = str[2];
        String result = "";


        //System.out.println("#### Background Task URL ="+url);
        System.out.println("#####  in doInBackground "+str[0]+" "+str[1]+" "+str[2]);
        try {
            switch (method) {
                case "GET": {
                    //call get method
                    System.out.println("#####  methodname = "+methodName);
                    switch (methodName) {
                        case "interests":
                            //strURL.concat("/interests");
                            strURL += "/interests";
                            break;
                        case "activities":
                            //strURL.concat("/activities");
                            strURL += "/activities/";
                            System.out.println("#####  Here "+strURL);
                            break;
                        case "messages":
                            //strURL.concat("/messages");
                            strURL += "/messages/";
                            break;
                        default:
                    }

                    result = ServerDataRetriever.invokeGet(strURL, params);
                }
                    break;
                case "POST": {
                    //call post method
                    switch (methodName) {
                        case "createnewactivity":
                            //strURL.concat("/activity");
                            strURL += "/createnewactivity";
                            break;
                        case "createnewmessage":
                            //strURL.concat("/message");
                            strURL += "/createnewmessage";
                            break;
                        case "user":
                            //strURL.concat("/message");
                            strURL += "/user";
                            break;
                        case "likeactivity":
                            //strURL.concat("/message");
                            strURL += "/likeactivity";
                            break;
                        case "otpconfirm":
                            //strURL.concat("/message");
                            strURL += "/otpconfirm";
                            break;
                        case "shareactivity":
                            //strURL.concat("/message");
                            strURL += "/shareactivity";
                            break;

                        default:

                    }

                    result = ServerDataRetriever.invokePost(strURL, params);
                }
                    break;
                default:
            }
            //String result = ServerDataRetriever.getFromServer(method, methodName, url);
            System.out.println("@@@@ Value = "+result+" Done");
            System.out.println("@@@@ Value = "+result+" Done");
            return result;
        } catch (Exception e) {
            System.out.println("Hello world");
            return new String();
        }
    }

    protected void onPostExecute(String result) {
        System.out.println("#####  Done String = "+result);


        try {
            //JSONObject respJson = new JSONObject(result);
            JSONObject jsonObject = new JSONObject(result);//respJson.getJSONObject("object");
            String type = (String)jsonObject.getString("type");
            if (type.equals("activityget")) {
                ArrayList<ActivityData> activityData = new ArrayList<ActivityData>();
                JSONArray groupArray = jsonObject.getJSONArray("activities");

                for (int i = 0; i < groupArray.length(); i++) {
                    JSONObject group = groupArray.getJSONObject(i);
                    String interestId = group.getString("interest");
                    String userid = group.getString("userid");
                    String username = group.getString("username");
                    String activityId = group.getString("activityid");
                    String activity = group.getString("activity");
                    int likes = group.getInt("likes");
                    int shares = group.getInt("shares");
                    String date = group.getString("date");
                    String place = group.getString("place");
                    String time = group.getString("time");
                    JSONArray location = group.getJSONArray("location");
                    double latitude = location.getDouble(0);
                    double longitude = location.getDouble(1);
                    activityData.add(new ActivityData(interestId, userid, username, activityId, latitude, longitude, activity, place, date, time, likes, shares));
                    //System.out.println(latitude + " " + longitude + " " + activity + " " + activity);
                    System.out.println("Done here");
                }
                ((MainActivity)(this.activity)).setActivities(activityData);
            }
            else if (type.equals("messageget")) {
                ArrayList<MessageData> msgData = new ArrayList<MessageData>();
                String activityId = jsonObject.getString("activityid");
                int messageCount = jsonObject.getInt("count");
                JSONArray messageArray = jsonObject.getJSONArray("messages");

                int msgLength = messageArray.length();
                for (int i = 0; i < msgLength; i++) {
                    JSONObject message = messageArray.getJSONObject(msgLength - i - 1);
                    String username = message.getString("username");
                    String msg = message.getString("message");
                    msgData.add(new MessageData(username, msg));
                    System.out.println("#### Message "+username+" "+msg);
                }
                ((MessageActivity)this.activity).setMessages(messageCount, msgData);
            }
            else if (type.equals("interestget")) {
                ArrayList<String> interestData = new ArrayList<String>();
                String interestId;
                JSONArray interestArray = jsonObject.getJSONArray("interests");

                for (int i = 0; i < interestArray.length(); i++) {
                    JSONObject interest = interestArray.getJSONObject(i);
                    interestId = interest.getString("interestid");
                    String interestName = interest.getString("interest");
                    interestData.add(interestName);
                    System.out.println("#### Message "+interestId+" "+interestName);
                }
                ((MainActivity)this.activity).setInterests(interestData);
            }
            else if (type.equals("activitypost")) {
                ArrayList<InterestData> interestData = new ArrayList<InterestData>();
                String ret = (String)jsonObject.getString("result");
                System.out.println("#####  Result = "+ret);
                ((CreateActivity)this.activity).creationCompleted(true);
                //finish();
            }
            else if (type.equals("otpconfirm")) {
                //ArrayList<InterestData> interestData = new ArrayList<InterestData>();
                String ret = (String)jsonObject.getString("result");
                JSONObject userdetails = jsonObject.getJSONObject("userdetail");
                String userid = userdetails.getString("userid");
                String username = userdetails.getString("username");
                String imsi = userdetails.getString("imsi");
                System.out.println("#####  Result = "+ret);
                ((LoginActivity)this.activity).otpConfirm(true, userid, username, imsi);
                //((LoginActivity)this.activity).otpConfirm(true, userid, "Gautam", "5129984612");
                //finish();
            }
            else if (type.equals("userpost")) {
                //ArrayList<InterestData> interestData = new ArrayList<InterestData>();
                String userid = (String)jsonObject.getJSONObject("userdetail").getString("userid");
                System.out.println("#####  UserID = "+userid);
                UserCredentials.setUserId(userid);
                //finish();
            }
        } catch (Exception e) {
            System.out.println("JSON parse error"+e.toString());
        }

        //this.activity.setGroups(groupData);

    }
}
