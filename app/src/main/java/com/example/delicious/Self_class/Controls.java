package com.example.delicious.Self_class;

import android.app.Application;

import java.util.List;

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
