package com.wf.nearlinescheduler.util;

public class Time {
    public static Long getCurrentTimeInSecs() {
        return System.currentTimeMillis()/1000L;
    }
}
