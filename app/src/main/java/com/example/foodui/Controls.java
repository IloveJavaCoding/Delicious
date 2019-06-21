package com.example.foodui;

import android.app.Application;

public class Controls extends Application {
    private static int lock;

    public Controls() {
    }

    public static int getLock() {
        return lock;
    }

    public static void setLock(int lock) {
        Controls.lock = lock;
    }
}
