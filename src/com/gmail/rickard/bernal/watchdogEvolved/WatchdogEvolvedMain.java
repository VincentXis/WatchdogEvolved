package com.gmail.rickard.bernal.watchdogEvolved;

import com.gmail.rickard.bernal.fileManagement.FileHandler;

public class WatchdogEvolvedMain {
    public static void main(String[] args) {
        String url = "http://www.patrickrothfuss.com/content/index.asp";
        FileHandler fileHandler = new FileHandler(url);
    }
}
