package com.example.annauniversity;

public class Upcoming_RC_Data {
    private final String GuestName;
    private final String MeetingTitle;
    private final int Day;
    private final String Month;

    public Upcoming_RC_Data(String GuestName, String MeetingTitle, int Day, String Month){

        this.GuestName=GuestName;
        this.MeetingTitle=MeetingTitle;
        this.Day = Day;
        this.Month = Month;


    }

    public String getGuestName(){
        return GuestName;
    }
    public String getMeetingTitle(){
        return MeetingTitle;
    }

    public int getDay(){
        return Day;
    }
    public String getMonth(){
        return Month;
    }

}
