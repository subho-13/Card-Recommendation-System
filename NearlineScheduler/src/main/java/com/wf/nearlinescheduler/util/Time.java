package com.wf.nearlinescheduler.util;

public class Time {
    private Time()
    {
        //do nothing
    }
    public static Long getCurrentTimeInSecs() {
        return System.currentTimeMillis()/1000L;
    }
}
