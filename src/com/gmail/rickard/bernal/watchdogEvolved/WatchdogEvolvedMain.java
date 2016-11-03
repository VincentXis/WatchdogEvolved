package com.gmail.rickard.bernal.watchdogEvolved;

import com.gmail.rickard.bernal.fileManagement.FileHandler;

public class WatchdogEvolvedMain {
    public static void main(String[] args) {
        String url = "http://liveclock.net/";
        FileHandler fileHandler = new FileHandler(url);
    }
}
